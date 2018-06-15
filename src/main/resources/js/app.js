import angular from 'angular';
import uirouter from '@uirouter/angularjs';

import registerRoutes from './routes';

import HelloWorld from './components/helloWorld';

const myApp = angular.module('myApp', [uirouter]);

// Setting Config
myApp.config($stateProvider => {
    registerRoutes($stateProvider);
});

myApp.component('helloWorld', HelloWorld);

