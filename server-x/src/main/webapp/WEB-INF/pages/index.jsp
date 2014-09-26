<html ng-app="CfChatClient">
    <header>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"/>

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css"/>

        <script language="JavaScript" src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
        <script language="JavaScript" src="//cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.10.0/ui-bootstrap.min.js"></script>
        <script language="JavaScript" src="//cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.10.0/ui-bootstrap-tpls.min.js"></script>


    </header>
    <body>


        <div ng-controller="CfChatCtrl" ng-init="connect()">
        <br/>
            <div class="container">
                <div class="col-md-8 col-md-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <form role="form">
                                <div class="container-fluid">
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" ng-model="message" placeholder="Enter message">
                                    </div>
                                    <div class="col-md-2">
                                        <button class="btn btn-primary" ng-click="sendName()">Send</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="panel-body">
                            <div ng-repeat="msg in msgs | orderBy: 'timestamp' : true">
                            <p>
                            <div class="container-fluid">
                                <div class="col-md-5">
                                    <strong style="color:#428bca">{{msg.timestamp}}</strong>
                                    <br/>
                                    <strong>{{msg.sender}}</strong></div>
                                <div class="col-md-7">
                                    {{msg.message}}
                                </div>
                            </div>
                            </p>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <script>
            var app = angular.module('CfChatClient', ['ui.bootstrap']);

            app.controller('CfChatCtrl', function($scope, $http){
                $scope.msgs = [];
                $scope.connected = false;
                $scope.message = "";

                $scope.setConnected = function (connected) {
                    $scope.connected = connected;
                    // document.getElementById('connect').disabled = connected;
                    // document.getElementById('disconnect').disabled = !connected;
                    // document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
                    // document.getElementById('response').innerHTML = '';
                }

                $scope.connect = function () {
                    if ('WebSocket' in window) {
                          console.log('Websocket supported');
                          socket = new WebSocket('ws://localhost:8080/server-x/websocket');

                          console.log('Connection attempted');

                          socket.onopen = function() {
                               console.log('Connection open!');
                               $scope.setConnected(true);
                               socket.send(JSON.stringify({ 'message': 'message' }));

                          }

                          socket.onclose = function() {
                              console.log('Disconnecting connection');
                          }

                          socket.onmessage = function (evt) {
                              var received_msg = evt.data;
                              console.log(received_msg);
                              console.log('message received!');
                              $scope.showMessage(received_msg);
                          }

                        } else {
                          console.log('Websocket not supported');
                        }
                }

                $scope.disconnect = function () {
                    $scope.setConnected(false);
                    console.log("Disconnected");
                }

                $scope.sendName = function() {
                    // var message = document.getElementById('message').value;
                    //socket.send(JSON.stringify({ 'message': 'message' }));

                    var params = {
                        sender:'Eva Vejstrup',
                        message:$scope.message,
                        timestamp:Date.now()
                    };
                    $http.post('/server-x/event',params).success(function(data){
                        console.log("REQUEST SUCCESS");
                    });
                }

                $scope.showMessage = function (message) {
                    var msg = angular.fromJson(message)
                    $scope.msgs.push(msg);
                }
            });
        </script>

            <script type="text/javascript">


        </script>
    </body>
</html>