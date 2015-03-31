'use strict';

require("bootstrap-webpack");

var React = require('react/addons');
var ReactTransitionGroup = React.addons.TransitionGroup;
var $ = require('jquery');


var t = require('tcomb-form');
var Form = t.form.Form;

var domain_name;

var FormationList = React.createClass({

	getInitialState: function() {
    return {
      domain_objs: []
    };
  },

	componentDidMount: function() {
		$.get('http://localhost:8080/formation/list', function(data) {
			if(this.isMounted()){
				this.setState({
					domain_objs: data
				});
			}
		}.bind(this));
	 },

  onClick: function(obj_index) {
  	this.props.onItemSelect(this.state.domain_objs[obj_index]);
  },

  render: function() {
  	var lis = [];
  	this.state.domain_objs.forEach(function(obj, i){
  		lis.push(<li key={i}><a onClick={this.onClick.bind(this, i)}>{obj}</a></li>);
  	}, this);
    return (
    	<div>
    		<ul>
    			{lis}
    		</ul>
    	</div>
    );
  }

});

var Formation = React.createClass({
	
	getInitialState: function(){
		return { domain: t.struct({}) };
	},

	componentWillReceiveProps: function(nextProps){
		if(this.props.domain !== nextProps.domain){
			this.selectedCategory = {};
			this.build(nextProps.domain);
		}
	},

	componentDidMount: function() {},

	build: function(domainName){
		var start = new Date().getTime();
		console.log('=========== Fetching formation object ['+domainName+'] ===========');

		$.get('http://localhost:8080/formation/describe', {object: domainName}, function(data) {
			console.log('Starting build of:', data);
			if(this.isMounted()){
				this.evaluate(data).done(function(domain){
					var end = new Date().getTime();
					var time = end - start;
					console.log('=========== Done building:', domain, 'Time taken ['+time+'ms] ===========');
					this.setState({
						domain: t.struct(domain),
						domain_name: data.objectName
					});
				}.bind(this));

			}
		}.bind(this));
	},

	evaluate: function(data){
		var deferred = $.Deferred();
		
		setTimeout(function(){
			var domain = {}, all = [], options = {fields:{}};

			console.debug('Building form for:', data);
			data.propertyHolders.forEach(function(holder, index, holders){
				console.debug('Evaluating: ', holder);
				
				if(this.selectedCategory.hasOwnProperty(holder.properyName)){
					holder.objectPropertyDescriptor.selectedCategory = this.selectedCategory[holder.properyName];
				}
				var p = this.resolveType(holder.propertyGeneralType, holder.propertyTypeDescriptor, holder.objectPropertyDescriptor)
				p.done(function(type){ 
					if(type) domain[holder.properyName] = type; 
				});
				all.push(p);
			}.bind(this));

			$.when.apply($, all).then(function(){ deferred.resolve(domain); });

		}.bind(this), 10);

	 	return deferred.promise();
	},

	selectedCategory:{},

	resolveType: function(type, tDescriptor, pDescriptor){
		var deferred = $.Deferred();
		setTimeout(function(){
			console.debug('Resolving type '+type+' with tDescriptor types', tDescriptor);
			switch (type){
				case 'String':
					deferred.resolve(t.Str);
					break;
				case 'Boolean':
					deferred.resolve(t.Bool);
					break;
				case 'Integer':
				case 'Long':
					deferred.resolve(t.Num);
					break;
				case 'List':
					this.resolveType(tDescriptor.innerTypes[0].generalTypes[0], tDescriptor.innerTypes[0], pDescriptor).done(function(listType){
						deferred.resolve(t.list(listType));
					});
					break;
				case 'Map':
					var entry = {},
					q1 = this.resolveType(tDescriptor.innerTypes[0].generalTypes[0], tDescriptor.innerTypes[0], pDescriptor),
					q2 = this.resolveType(tDescriptor.innerTypes[1].generalTypes[0], tDescriptor.innerTypes[1], pDescriptor);

					//var k = pDescriptor.mapKeyLabel || 'Key', v = pDescriptor.mapValueLabel || 'Value';
					q1.done(function(key){ entry['Key'] = key });
					q2.done(function(value){ entry['Value'] = value });

					$.when(q1, q2).then(function(){
						console.log(entry);
						deferred.resolve(t.list(t.struct(entry)));
					});
					break;
				case 'Enum':
					var enumType = {};
					pDescriptor.values.forEach(function(value, index, values){
						enumType[value] = value;
					});
					deferred.resolve(t.enums(enumType));
					break;
				case 'Interface':
					console.debug('Type ['+pDescriptor.typeCategory+'] is an interface / abstract type, calling category');

					$.get('http://localhost:8080/formation/category', {category: pDescriptor.typeCategory}, function(data) {
						console.debug('Interface / Abstract category ['+pDescriptor.typeCategory+'] described as:', data);

						var types = {}
						data.forEach(function(holder, index, values){
							types[holder.objectName] = holder.objectName;
						});
						deferred.resolve(t.enums(types));

						// this.ask(pDescriptor.selectedCategory).done(function(data){
						// 	this.evaluate(data).then(function(domain){
						// 		interfaceType[data.objectName] = t.struct(domain);
						// 		deferred.resolve(t.struct(interfaceType));
						// 	});
						// };

					}.bind(this));
					break;
				default: 
					console.debug('Type ['+type+'] is a complex type, calling formation.describe');
					this.ask(type).done(function(data){
						this.evaluate(data).then(function(domain){
							deferred.resolve(t.struct(domain));
						});
					}.bind(this));
					break;
			}
		}.bind(this), 10);
		return deferred.promise();
	},

	cache: {},
	ask: function(type){
		var deferred = $.Deferred();
		setTimeout(function(){
			if(this.cache.hasOwnProperty(type)){
				deferred.resolve(this.cache[type]);
			}else{
				$.get('http://localhost:8080/formation/describe', {object: type}, function(data) {
					console.debug('Complex type ['+type+'] described as:', data);
					this.cache.type = data;
					deferred.resolve(data);
				}.bind(this));
			}
		}.bind(this), 0);
		return deferred.promise();
	},

	onClick: function () {
	  var value = this.refs.domain.getValue();
	  if (value) console.log(value);
	},

	onChange: function(value){
	},

	render: function(){
		return (
			<div>
				<h4>{this.state.domain_name}</h4>
				<Form ref="domain" type={this.state.domain} options={this.state.options} onChange={this.onChange}/>
				<button onClick={this.onClick} className="btn btn-primary">Submit</button>
			</div>
		);
	}
});

var FormationDemo = React.createClass({
	getInitialState: function() {
    return { domainName: {} };
  },

	onItemSelect: function(item){
		this.setState({domainName: item});
	},

	render: function(){
		return(
			<div>
				<div className="row">
		      <div className="col-lg-6">
		        <FormationList onItemSelect={this.onItemSelect}/>
		      </div>
		    </div>
		    <div className="row">
		      <div className="col-lg-12">
		        <Formation domain={this.state.domainName}/>
		      </div>
		    </div>
		  </div>
		);
	}
});













var TransportType = t.enums.of('Car Bike');

var Car = t.struct({
  carField1: t.Str,
  carField2: t.Str
});

var Bike = t.struct({
  bikeField1: t.Str,
  bikeField2: t.Str
});

// returns the proper struct based on form's value
function getType(value) {
  var props = {
    type: TransportType
  };
  if (value.type === 'Car') {
    props.car = Car; // or t.mixin(props, Car.meta.props)
  }
  else if (value.type === 'Bike') {
    props.bike = Bike; // or t.mixin(props, Bike.meta.props)
  }
  return t.struct(props);
}

var App = React.createClass({

  getInitialState: function () {
    return {
      type: getType({}),
      value: {}
    };
  },

  onChange: function (value) {
    this.setState({
      type: getType(value),
      value: value
    });
  },

  save: function () {
    var value = this.refs.form.getValue();
    if (value) {
      console.log(value);
    }
  },

  render: function() {
    return (
      <div>
        <Form ref="form"
          type={this.state.type}
          value={this.state.value}
          onChange={this.onChange} />
        <button className="btn btn-primary" onClick={this.save}>Save</button>
      </div>
    );
  }

});

//React.render(<App />, document.getElementById('container'));
React.render(<FormationDemo />, document.getElementById('container'));

