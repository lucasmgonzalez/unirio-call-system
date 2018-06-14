const angular = require('angular');
const router = require('@uirouter/angularjs')

const myApp = angular.module('myApp', ['ui-router']);

myApp.config($stateProvider => {
    const indexState = {
        name: 'index',
        url: '/',
        component: '<app></app>'
    };

    $stateProvider.state(indexState);
});

myApp.component('app', {
    template: `It worked!`,
});

