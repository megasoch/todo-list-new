var loginApp = angular.module('todoApp', ['ngResource', 'ngRoute']);

loginApp.factory('TaskFactory', function ($resource) {
    return $resource('/tasks/:id', {id: '@id'},
        {
            update: {
                method: 'PUT'
            },

            remove: {
                method: 'DELETE'
            }
        });
});

loginApp.factory('DoneFactory', function ($resource) {
    return $resource('/done-tasks/:id', {id: '@id'},
        {
            remove: {
                method: 'DELETE'
            }
        });
});


loginApp.factory('LoginFactory', function ($resource) {
    return $resource('/create');
});

loginApp.controller('MenuCtrl', function ($scope, $resource, $location, $rootScope, $http, TaskFactory, DoneFactory, LoginFactory) {

    $scope.currentUser = 'user';
    $scope.noAccess = '';
    $scope.enableEdit = false;
    $scope.booleanLogin = true;
    $scope.booleanRegister = false;
    $scope.booleanList = false;
    $scope.tasks = [];
    $scope.doneTasks = [];
    $scope.updated = [];
    $scope.userAddInfo = '';
    $scope.updatedText = [];

    $scope.$on("$routeChangeSuccess", function () {
        if ($location.path() == '/list') {
            $scope.loadTasks();
        }
    });

    $scope.loadTasks = function () {
        $scope.tasks = TaskFactory.query(function () {
            $scope.updated.length = $scope.tasks.length;
        });
        $scope.doneTasks = DoneFactory.query();
    }

    $scope.editTask = function (index) {
        $scope.updatedText[index] = $scope.tasks[index].title;
        $scope.updated[index] = true;
    }

    $scope.cancelEdit = function (index) {
        $scope.updated[index] = false;
    }

    $scope.addUser = function (username, password) {
        LoginFactory.save({username: username, password: password}, function () {
            $location.path('/login');
        }, function () {
            $scope.userAddInfo = 'User with that username already exists!';
        });
    }

    $scope.login = function () {
        $location.path('/list');
    }

    $scope.logout = function () {
        $http.post('logout', {});
    }

    $scope.addTask = function (text) {
        TaskFactory.save({title: text}, function () {
            $scope.tasks = TaskFactory.query();
            $scope.updated.push(false);
        });

    }

    $scope.removeTask = function (task, index) {
        TaskFactory.remove({id: task.id}, function () {
            $scope.tasks = TaskFactory.query();
            $scope.updated.splice(index, 1);
        });
    }

    $scope.doneTask = function (task, index) {
        TaskFactory.remove({id: task.id}, function () {
            $scope.tasks = TaskFactory.query();
            $scope.updated.splice(index, 1)
            DoneFactory.save({userId: task.userId, title: task.title}, function () {
                $scope.doneTasks = DoneFactory.query();
            });
        });
    }

    $scope.updateTask = function (task, text, index) {
        task.title = text;
        TaskFactory.update(task, function () {
            $scope.tasks = TaskFactory.query();
            $scope.updated[index] = false;
        });
    }

    $scope.removeDone = function (done) {
        DoneFactory.remove({id: done.id}, function () {
            $scope.doneTasks = DoneFactory.query();
        });
    }

    $scope.revertDone = function (done) {
        DoneFactory.remove({id: done.id}, function () {
            $scope.doneTasks = DoneFactory.query();
            $scope.updated.push(false);
            TaskFactory.save({userId: done.userId, title: done.title}, function () {
                $scope.tasks = TaskFactory.query();
            });
        });
    }

    $scope.registerPage = function () {
        $location.path('/register');
        $scope.userAddInfo = '';
    }

    $scope.loginPage = function () {
        $location.path('/login');
    }

});

loginApp.config(['$routeProvider', '$locationProvider', '$httpProvider', function ($routeProvider, $locationProvider, $httpProvider) {

    $routeProvider

        .when('/', {
            templateUrl: '/login.html',
            controller: 'MenuCtrl'
        })

        .when('/register', {
            templateUrl: '/register.html',
            controller: 'MenuCtrl'
        })

        .when('/list', {
            templateUrl: '/list.html',
            controller: 'MenuCtrl'
        })

        .otherwise({
            redirectTo: '/'
        });

    //$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

}]);