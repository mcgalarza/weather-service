angular.module('app.controllers', [])
.controller('BoardListController', function($scope, $state, popupService, $window, Board) {
  $scope.boards = Board.query(); //fetch all boards. Issues a GET to /boards
})
.controller('BoardViewController', function($scope, $stateParams, Board) {
  $scope.locations = Board.query({ id: $stateParams.id }); //Get a single board.Issues a GET to /boards/:id
})
.controller('BoardCreateController', function($scope, $state, $stateParams, Board) {
  $scope.board = new Board();  //create new board instance. Properties will be set via ng-model on UI

  $scope.addBoard = function() { //create a new board. Issues a POST to /boards
    $scope.board.$save(function() {
      $state.go('boards'); // on success go back to the list i.e. boards state.
    });
  };
})
.controller('LocationCreateController', function($scope, $state, $stateParams, Location) {
	$scope.location = new Location();
	
	$scope.addLocation = function() {
		$scope.location.$save(function() {
			$state.go('boards');
		});
	};
})
