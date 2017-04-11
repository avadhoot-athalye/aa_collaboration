var user = angular.module('userModule', []);

user.directive('fileModel', ['$parse', 
    function ($parse){
        return {
            restrict : 'A',
            link : function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;
                element.bind('change', function() {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);

user.factory('userFactory', ['$http', '$q', '$rootScope',

    function($http, $q, $rootScope) {

        //For linking backend project with the frontend
        var url = 'http://localhost:7070/webapp';
        
        return {
            userBlogList : userBlogList,
            userJobList : userJobList,
            userEventList : userEventList,
            uploadFile : uploadFile,
            fetchJobsApplied : fetchJobsApplied,
            fetchEventJoined : fetchEventJoined
        };

         //Function to fetch userblog list
         
         function userBlogList(id) {
            console.log('Inside factory now');
            var deferred = $q.defer();

            $http.get(url + '/user/blogs/list/'+ id)
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
        }

        //Function to fetch jobs user has created
         
         function userJobList(id) {
            console.log('Inside factory now');
            var deferred = $q.defer();

            $http.get(url + '/user/jobs/list/'+ id)
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
        }

         //Function to fetch user event list
         
         function userEventList(id) {
            console.log('Inside factory now');
            var deferred = $q.defer();

            $http.get(url + '/user/events/list/'+ id)
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
        }

        // uploadFile function to upload the image on the server
         function uploadFile(file) {
                 var deferred = $q.defer();
                
                 var fd = new FormData();
                 fd.append('file', file);
                fd.append('id', $rootScope.user.id);
                $http.post(url + '/upload/profile-picture', fd, {
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
               })
                .then(
                    function (response) {
                    deferred.resolve(response.data);
                },
                    function (error) {
                    console.log(error);
                    deferred.reject(error);
                }
            );
        return deferred.promise;
        }
    
        //funcion to fetch jobs applied
        function fetchJobsApplied(id) {
             var deferred = $q.defer();

              $http.get(url + '/user/jobs/applied/'+ id)
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;

        }

         //calling method to fetch events user applied for
        function fetchEventJoined(id) {
             var deferred = $q.defer();

              $http.get(url + '/user/events/joined/'+ id)
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;

        }
    
    }
])