/*!
 * 
 * Angle - Bootstrap Admin App + AngularJS
 * 
 * Author: @themicon_co
 * Website: http://themicon.co
 * License: http://support.wrapbootstrap.com/knowledge_base/topics/usage-licenses
 * 
 */

if (typeof $ === 'undefined') {
    throw new Error('This application\'s JavaScript requires jQuery');
}

// APP START
// ----------------------------------- 

var App = angular.module('seastar', [
    'ngRoute',
    'ngAnimate',
    'LocalStorageModule',
    'ui.bootstrap',
    'ui.router'
]);

App.run(["$rootScope", "$state", "$stateParams", "$window", "sessionStorageService", 'constants', '$location','localStorageService',
    function ($rootScope, $state, $stateParams, $window, sessionStorageService, constants, $location,localStorageService) {
        // Set reference to access them from any scope


        $rootScope.cdn = {
            addr: 'master'
        };


    }]);
