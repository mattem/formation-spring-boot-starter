'use strict';

describe('Main', function () {
  var React = require('react/addons');
  var JsApp, component;

  beforeEach(function () {
    var container = document.createElement('div');
    container.id = 'content';
    document.body.appendChild(container);

    JsApp = require('components/JsApp.js');
    component = React.createElement(JsApp);
  });

  it('should create a new instance of JsApp', function () {
    expect(component).toBeDefined();
  });
});
