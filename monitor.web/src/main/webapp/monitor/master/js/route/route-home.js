/**=========================================================
 * Module: route-home.js
 * App routes and resources configuration
 =========================================================*/
App.config(['$stateProvider', '$locationProvider', '$urlRouterProvider', 'RouteHelpersProvider',
    function ($stateProvider, $locationProvider, $urlRouterProvider, helper) {
        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: helper.basepath('home.html')
            })
            .state('addGatherConfig', {
                url: '/addGatherConfig',
                templateUrl: helper.basepath('addGatherConfig.html')
            })
            .state('quotaQuery', {
                url: '/quotaQuery?monitorId',
                templateUrl: helper.basepath('quotaQuery.html')
            })
            .state('addComputeFormula', {
                url: '/addComputeFormula',
                templateUrl: helper.basepath('addComputeFormula.html')
            })
            .state('addGatherFile', {
                url: '/addGatherFile',
                templateUrl: helper.basepath('addGatherFile.html')
            })
            .state('addMonitorFormula', {
                url: '/addMonitorFormula',
                templateUrl: helper.basepath('addMonitorFormula.html')
            })
            .state('queryComputeFormula', {
                url: '/queryComputeFormula',
                templateUrl: helper.basepath('queryComputeFormula.html')
            })
            .state('queryGatherFile', {
                url: '/queryGatherFile',
                templateUrl: helper.basepath('queryGatherFile.html')
            })
            .state('queryMonitorFormula', {
                url: '/queryMonitorFormula',
                templateUrl: helper.basepath('queryMonitorFormula.html')
            })
            .state('dapan', {
                url: '/dapan',
                templateUrl: helper.basepath('dapan.html')
            })


        ;
    }])
;
