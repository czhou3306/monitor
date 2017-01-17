/**
 * Created by huwenfei on 2015/12/2.
 */
/**=========================================================
 * Module: controller-login.js
 * login Application Controller
 =========================================================*/

App.service('monitorService',
    ['$state', '$http', '$timeout',  'constants',
        function ($state, $http, $timeout, constants) {

            this.ask = function (askUserId, answerUserId,amount,questionContent) {
                return $http.post(constants.serveUrl + '/ask', {
                    askUserId: askUserId,
                    answerUserId: answerUserId,
                    amount:amount,
                    questionContent:questionContent
                });
            };

            this.addgatherconfig = function (systemName, filePath,regex,instance,groupFields,functionName,position) {

                var request = {};
                request["filePath"] = filePath;
                request["regex"]=regex;
                request["groupFields"]=groupFields;
                request["systemName"]=systemName;
                request["instance"]=instance;

                var formulaList=[];
                formulaList.push({"function":functionName,"position":position});
                request["formulaList"] = formulaList;

                return $http.post(constants.serveUrl + '/manager/addgatherconfig.html', request);
            };

            this.split = function(regex,instance,filePath) {
                return $http.post(constants.serveUrl + '/manager/instanceSplit.html', {
                    "regex": regex,
                    "instance": instance,
                    "filePath": filePath
                });
            };




        }]);
