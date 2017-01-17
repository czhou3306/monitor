/**=========================================================
 * Module: controller-home.js
 * Main Application Controller
 =========================================================*/

App.controller('indexController',
    ['$rootScope', '$scope', '$state', '$window', '$timeout', '$location','monitorService',
        function($rootScope, $scope, $state, $window, $timeout, $location,monitorService) {


            $scope.submit = function(){
                monitorService.ask($scope.askUserId, $scope.answerUserId,$scope.amount,$scope.content).success(function(resp){

                });
            }


        }])
    .controller('gatherConfigController',
    ['$rootScope', '$scope', '$state', '$window', '$timeout', '$location','monitorService',
        function($rootScope, $scope, $state, $window, $timeout, $location,monitorService) {


            $scope.systemName = "mobile";
            $scope.filePath = "/opt/logs/mobile/remote-digest.log";
            $scope.regex = "\"ms\",\",\",\"[(\",\")]\"";
            $scope.instance = "2016-11-16 13:46:20,082 [(BusinessActionDAO.getByActivityId,S,5ms)]<uuid=6babec430e02ed38b20649ee>";
            $scope.groupFields = "$2,$3";
            $scope.functionName = "sum";
            $scope.position = "$4";
            $scope.gatherType = "LINE";
            $scope.filterCondition = "$7=E,$6=S";
            $scope.showField = "$6,$7";

            $scope.monitorFormula = "#3#/#4#";


            $scope.submit = function(){
                monitorService.addgatherconfig($scope.systemName, $scope.filePath,$scope.regex,$scope.instance,$scope.groupFields,$scope.functionName,$scope.position).success(function(resp){
                    alert("ok");
                });
            }

            $scope.split = function(){
                monitorService.split($scope.regex,$scope.instance).success(function(resp){
                    console.log(resp);
                });
            }

        }])
    .controller('addComputeFormulaController',
        ['$rootScope', '$scope', '$state', '$window', '$timeout', '$location','monitorService','$http','constants',
            function($rootScope, $scope, $state, $window, $timeout, $location,monitorService,$http,constants) {

                $scope.functionName = "sum";
                $scope.position = "$4";
                $scope.filterCondition = "$7=E,$6=S";

                $scope.addComputeFormula = function() {
                    $http.post(constants.serveUrl + '/manager/addComputeFormula.html', {
                        fileId: $scope.fileId,
                        formulaType: $scope.functionName,
                        fieldPosition: $scope.position,
                        filterCondition: $scope.filterCondition,
                        computeName:$scope.computeName
                    }).success(function(resp){
                        if(resp.code == constants.successCode) {
                            $scope.computeId = resp.data.computeId;
                            alert("成功");
                        }else{
                            alert(resp.msg);
                        }
                    });
                };

            }])
    .controller('addGatherFileController',
        ['$rootScope', '$scope', '$state', '$window', '$timeout', '$location','monitorService','$http','constants',
            function($rootScope, $scope, $state, $window, $timeout, $location,monitorService,$http,constants) {

                $scope.systemName = "mobile";
                $scope.filePath = "/opt/apache-tomcat/logs/localhost_access_log.{yyyy-MM-dd}.txt";
                $scope.regex = "\"ms\",\",\",\"[(\",\")]\"";
                $scope.instance = "172.16.160.60 - - [12/Dec/2016:14:57:47 +0800] \"POST /v1/product/detail HTTP/1.0\" 200 2846";
                $scope.groupFields = "$2,$3";
                $scope.gatherType = "LINE";

                $scope.addGatherFile = function() {
                    $http.post(constants.serveUrl + '/manager/addGatherFile.html', {
                        systemName: $scope.systemName,
                        filePath: $scope.filePath,
                        splitRegex: $scope.regex,
                        groupFieldPosition: $scope.groupFields,
                        instance: $scope.instance,
                        gatherType: $scope.gatherType
                    }).success(function(resp){
                        if(resp.code == constants.successCode) {
                            alert("成功");
                            $scope.fileId = resp.data.fileId;
                        }else{
                            alert(resp.msg);
                        }
                    });
                };

                $scope.split = function(){
                    monitorService.split($scope.regex,$scope.instance,$scope.filePath).success(function(resp){
                        if(resp.code == constants.successCode) {
                            $scope.splitResult =  resp.data.splitResult ;

                        }else{
                            alert(resp.msg);
                        }
                    });
                }
            }])
    .controller('addMonitorFormulaController',
        ['$rootScope', '$scope', '$state', '$window', '$timeout', '$location','monitorService','$http','constants',
            function($rootScope, $scope, $state, $window, $timeout, $location,monitorService,$http,constants) {

                $scope.showField = "$6,$7";
                $scope.monitorFormula = "#3#/#4#";

                $scope.addMonitorFormula = function() {
                    $http.post(constants.serveUrl + '/manager/addMonitorFormula.html', {
                        fileId: $scope.fileId,
                        monitorName: $scope.monitorName,
                        showField: $scope.showField,
                        formula: $scope.monitorFormula
                    }).success(function(resp){
                        if(resp.code == constants.successCode) {
                            $scope.monitorId = resp.data.monitorId;
                            alert("成功");
                        }else{
                            alert(resp.msg);
                        }
                    });
                };


            }])
    .controller('queryComputeFormulaController',
        ['$rootScope', '$scope', '$state', '$window', '$timeout', '$location','monitorService','$http','constants',
            function($rootScope, $scope, $state, $window, $timeout, $location,monitorService,$http,constants) {

                $http.get(constants.serveUrl + '/manager/queryComputeFormula.html').success(function(resp){
                    console.log(resp);
                    if(resp.code == constants.successCode){
                        $scope.dataList = resp.data.computeFormulaList;
                    }
                });

                $scope.openModal = function(data){
                    $scope.chooseRecord = data;
                    $('#updateComputeFormulaModal').modal({
                        keyboard: true
                    })
                };

                $scope.updateComputeFormula = function(fileId,computeId,computeName,formulaType,fieldPosition,filterCondition) {
                    $http.post(constants.serveUrl + '/manager/updateComputeFormula.html', {
                        fileId: fileId,
                        computeId:computeId,
                        computeName:computeName,
                        formulaType: formulaType,
                        fieldPosition: fieldPosition,
                        filterCondition: filterCondition
                    }).success(function(resp){
                        if(resp.code == constants.successCode) {
                            alert("更新成功");
                            $('#updateComputeFormulaModal').modal('hide')
                        }else{
                            alert(resp.msg);
                        }
                    });
                };


            }])
    .controller('queryGatherFileController',
        ['$rootScope', '$scope', '$state', '$window', '$timeout', '$location','monitorService','$http','constants',
            function($rootScope, $scope, $state, $window, $timeout, $location,monitorService,$http,constants) {

                $http.get(constants.serveUrl + '/manager/queryGatherFile.html').success(function(resp){
                    if(resp.code == constants.successCode){
                        $scope.dataList = resp.data.gatherFileList;
                    }
                });


                $scope.openModal = function(data){
                    $scope.chooseRecord = data;
                    $scope.splitResult = null;
                    $('#updateGatherFileModal').modal({
                        keyboard: true
                    })
                    }

                $scope.split = function(regex,instance,filePath){
                    monitorService.split(regex,instance,filePath).success(function(resp){
                        if(resp.code == constants.successCode) {
                            $scope.splitResult =  resp.data.splitResult ;
                        }else{
                            alert(resp.msg);
                        }
                    });
                };

                $scope.updateGatherFile = function(fileId,systemName,filePath,splitRegex,groupFieldPosition,instance,gatherType) {
                    $http.post(constants.serveUrl + '/manager/updateGatherFile.html', {
                        fileId:fileId,
                        systemName: systemName,
                        filePath: filePath,
                        splitRegex: splitRegex,
                        groupFieldPosition: groupFieldPosition,
                        instance: instance,
                        gatherType: gatherType
                    }).success(function(resp){
                        if(resp.code == constants.successCode) {
                            alert("更新成功");
                            $('#updateGatherFileModal').modal('hide')
                            $scope.fileId = resp.data.fileId;
                        }else{
                            alert(resp.msg);
                        }
                    });
                };


            }])
    .controller('queryMonitorFormulaController',
        ['$rootScope', '$scope', '$state', '$window', '$timeout', '$location','monitorService','$http','constants',
            function($rootScope, $scope, $state, $window, $timeout, $location,monitorService,$http,constants) {
                $http.get(constants.serveUrl + '/manager/queryMonitorFormula.html').success(function(resp){
                    console.log(resp);
                    if(resp.code == constants.successCode){
                        $scope.dataList = resp.data.monitorFormulaList;
                    }
                });


                $scope.openModal = function(data){
                    $scope.chooseRecord = data;
                    $scope.splitResult = null;
                    $('#updateMonitorFormulaModal').modal({
                        keyboard: true
                    })
                };

                $scope.updateMonitorFormula = function(monitorId,fileId,monitorName,showField,formula) {
                    $http.post(constants.serveUrl + '/manager/updateMonitorFormula.html', {
                        monitorId: monitorId,
                        fileId:fileId,
                        monitorName:monitorName,
                        showField: showField,
                        formula: formula,
                    }).success(function(resp){
                        if(resp.code == constants.successCode) {
                            alert("更新成功");
                            $('#updateMonitorFormulaModal').modal('hide')
                        }else{
                            alert(resp.msg);
                        }
                    });
                };

            }])

    .controller('quotaQueryController',
        ['$rootScope', '$scope', '$state', '$window', '$timeout', '$location','monitorService','$http','constants','$stateParams',
            function($rootScope, $scope, $state, $window, $timeout, $location,monitorService,$http,constants,$stateParams) {

                if($stateParams["monitorId"]){
                    $scope.monitorId = $stateParams["monitorId"];
                }

                $scope.query = function (monitorId,beginDate,endDate,timeGroup) {

                    $http.get(constants.serveUrl + '/quota/query.html', {
                        params: {
                            "monitorId": monitorId,
                            "beginDate":beginDate.Format("yyyyMMddhhmmss"),
                            "endDate": endDate.Format("yyyyMMddhhmmss"),
                            "timeGroup":timeGroup
                        }
                    }).success(function(resp){
                        if(resp.code == constants.successCode){
                            $scope.colNames = resp.data.colNames;
                            $scope.records = resp.data.records;

                            $scope.categories=[];
                            $scope.series = [];

                            for(index in  $scope.colNames){
                                var colName = $scope.colNames[index];
                                var obj = {"name":colName};
                                obj["data"] = [];
                                $scope.series.push(obj);
                            }

                            for (index in $scope.records){
                                for(colIndex in $scope.colNames){
                                    $scope.series[colIndex]["data"].unshift($scope.records[index][colIndex]);
                                }
                            }
                            $scope.categories = $scope.series[0]["data"];
                            $scope.series = $scope.series.splice(1);
                        }
                    });
                };

                function query(beginDate,endDate,timeGroup){
                    if($scope.monitorId){
                        if(!beginDate){
                            beginDate = new Date();
                            beginDate.setMinutes(beginDate.getMinutes() - 21, beginDate.getSeconds(), 0);
                        }
                        if(!endDate){
                            endDate = new Date();
                            endDate.setMinutes(endDate.getMinutes() - 1, endDate.getSeconds(), 0);
                        }
                        if(!timeGroup){
                            timeGroup = "M";
                        }
                        $scope.query($scope.monitorId,beginDate,endDate,timeGroup);
                    }
                }
                window.setInterval(function(){
                    if( $scope.autorefresh !== false){
                        query();
                    }
                },60*1000);

                window.setTimeout(function(){
                    query();
                });

                $scope.$on('refresh',function(evt, data){
                    query(data.beginDate,data.endDate,data.timeGroup);
                });

            }])
    .controller('dapanController',
        ['$rootScope', '$scope', '$state', '$window', '$timeout', '$location','monitorService','$http','constants','$stateParams',
            function($rootScope, $scope, $state, $window, $timeout, $location,monitorService,$http,constants,$stateParams) {

                $scope.beginDate  =  (new Date()).Format("yyyy-MM-dd hh:mm");
                $scope.endDate  =  (new Date()).Format("yyyy-MM-dd hh:mm");

                $scope.refresh = function(){
                    $scope.autorefresh = false;
                    $scope.$broadcast("refresh", { beginDate:new Date($scope.beginDate), endDate: new Date($scope.endDate),timeGroup:$("#timeGroup").val() });
                }

                $scope.autorefresh = true;
                $scope.timeGroup = 'M';
                $scope.changeAutoRefresh = function(){
                    $scope.timeGroup = 'M';
                    $scope.autorefresh = $scope.autorefresh ? false : true;
                }

                $scope.$watch('timeGroup',function(newValue,oldValue){
                    if(newValue != 'M'){
                        $scope.autorefresh = false;
                    }
                });

                $('.form_datetime').datetimepicker({
                    format: "yyyy-mm-dd hh:ii",
                    autoclose: true,
                    todayBtn: true,
                    pickerPosition: "bottom-left"
                });



            }])

;
