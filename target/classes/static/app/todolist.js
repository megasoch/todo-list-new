var loginApp = angular.module('todoApp', ['ngResource']);

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

loginApp.controller('MenuCtrl', function ($scope, $resource, TaskFactory, DoneFactory) {

    $scope.currentUser = 'user';
    $scope.noAccess = '';
    $scope.enableEdit = false;
    $scope.booleanLogin = true;
    $scope.booleanRegister = false;
    $scope.booleanList = false;
    $scope.tasks = [];
    $scope.doneTasks = [];
    $scope.updated = [];


    $scope.menu = function (log, reg, list) {
        $scope.booleanLogin = log;
        $scope.booleanRegister = reg;
        $scope.booleanList = list;
    };

    $scope.editTask = function(index) {
        $scope.updated[index] = true;
    }

    $scope.cancelEdit = function(index) {
        $scope.updated[index] = false;
    }

    $scope.addUser = function (username, password) {
    };

    $scope.login = function () {
        $scope.tasks = TaskFactory.query(function() {
            $scope.updated.length = $scope.tasks.length;
        });
        $scope.doneTasks = DoneFactory.query();
        $scope.menu(false, false, true);

    }

    $scope.addTask = function (text) {
        TaskFactory.save({userId: 1, title: text}, function () {
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

    $scope.doneTask = function (task) {
        TaskFactory.remove({id: task.id}, function () {
            $scope.tasks = TaskFactory.query();
            $scope.updated.push(false);
            DoneFactory.save({userId: task.userId, title: task.title}, function () {
                $scope.doneTasks = DoneFactory.query();
            });
        });
    }

    $scope.updateTask = function (task, text) {
        TaskFactory.update({id: task.id, userId: task.userId, title: text}, function() {
            $scope.tasks = TaskFactory.query();
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
            TaskFactory.save({userId: done.userId, title: done.title}, function () {
                $scope.tasks = TaskFactory.query();
            });
        });
    }
});