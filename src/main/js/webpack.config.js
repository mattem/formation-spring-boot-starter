/*
 * Webpack development server configuration
 *
 * This file is set up for serving the webpack-dev-server, which will watch for changes and recompile as required if
 * the subfolder /webpack-dev-server/ is visited. Visiting the root will not automatically reload.
 */
'use strict';
var webpack = require('webpack');

module.exports = {

  output: {
    filename: 'main.js',
    publicPath: '/assets/'
  },

  cache: true,
  debug: true,
  devtool: false,
  entry: [
      'webpack/hot/only-dev-server',
      './src/scripts/components/JsApp.js'
  ],

  stats: {
    colors: true,
    reasons: true
  },

  resolve: {
    extensions: ['', '.js'],
    alias: {
      'styles': '../../../src/styles',
      'components': '../../../src/scripts/components/'
    }
  },
  module: {
    preLoaders: [{
      test: /\.js$/,
      exclude: /node_modules/,
      loader: 'jsxhint'
    }],
    loaders: [{
      test: /\.js$/,
      exclude: /node_modules/,
      loader: 'react-hot!jsx-loader?harmony'
    }, {
      test: /\.sass/,
      loader: 'style-loader!css-loader!sass-loader?outputStyle=expanded'
    }, {
      test: /\.css$/,
      loader: 'style-loader!css-loader'
    }, {
      test: /\.(png|jpg)$/,
      loader: 'url-loader?limit=8192'
    },{ 
      test: /bootstrap\/js\//, 
      loader: 'imports?jQuery=jquery' 
    },{ 
      test: /\.woff(\?v=\d+\.\d+\.\d+)?$/,   
      loader: "url?limit=10000&minetype=application/font-woff" 
    },{ 
      test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,    
      loader: "url?limit=10000&minetype=application/octet-stream" 
    },{ 
      test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,    
      loader: "file" 
    },{ 
      test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,    
      loader: "url?limit=10000&minetype=image/svg+xml" 
    },{ 
      test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/,   
      loader: "file" 
    }],
  },

  plugins: [
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoErrorsPlugin()
  ]

};
