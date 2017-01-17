/**=========================================================
 * Module: route.js
 * App routes and resources configuration
 =========================================================*/

App.config(['$stateProvider', '$locationProvider', '$urlRouterProvider', 'RouteHelpersProvider', "sessionStorageServiceProvider", "localStorageServiceProvider",
    function ($stateProvider, $locationProvider, $urlRouterProvider, helper, sessionStorageServiceProvider, localStorageServiceProvider) {
        sessionStorageServiceProvider.setPrefix("seastarSession");
        localStorageServiceProvider.setPrefix('seastarLocal');
        // Set the following to true to enable the HTML5 Mode
        // You may have to set <base> tag in index and a routing configuration in your server
        $locationProvider.html5Mode(false);

        // default route
        $urlRouterProvider.otherwise('/dapan');

    }]).config(['$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
    function ($controllerProvider, $compileProvider, $filterProvider, $provide) {
        'use strict';
        // registering components after bootstrap
        App.controller = $controllerProvider.register;
        App.directive = $compileProvider.directive;
        App.filter = $filterProvider.register;
        App.factory = $provide.factory;
        App.service = $provide.service;
        App.constant = $provide.constant;
        App.value = $provide.value;

    }])
;
