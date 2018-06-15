import angular from 'angular';
import uirouter from '@uirouter/angularjs';

import HelloWorld from './components/helloWorld';

const myApp = angular.module('myApp', [uirouter]);

// Setting Config
myApp.config($stateProvider => {
    const indexState = {
        name: 'index',
        url: '',
        component: 'helloWorld'
    };

    $stateProvider.state(indexState);
});

myApp.component('helloWorld', HelloWorld);

