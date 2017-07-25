angular.module('app.controllers', [])
.controller('BoardListController', function($scope, $state, popupService, $window, Board) {
  $scope.boards = Board.query(); //fetch all boards. Issues a GET to /boards

  // $scope.deleteBoard = function(board) { // Delete a Board. Issues a DELETE to boards/:id
  //   if (popupService.showPopup('Really delete this?')) {
  //     board.$delete(function() {
  //       $scope.boards = Board.query(); 
  //       $state.go('boards');
  //     });
  //   }
  // };
}).controller('BoardViewController', function($scope, $stateParams, Board) {
  $scope.locations = Board.query({ id: $stateParams.id }); //Get a single board.Issues a GET to /boards/:id

  $scope.deleteLocation = function(location) { // Delete a Location. Issues a DELETE to boards/:id/:id
    // if (popupService.showPopup('Really delete this?')) {
      location.$delete(function() {
        $scope.locations = Board.query({ id: $stateParams.id }); 
        $state.go('locations');
      });
    // }
  };
}).controller('BoardCreateController', function($scope, $state, $stateParams, Board) {
  $scope.board = new Board();  //create new board instance. Properties will be set via ng-model on UI

  $scope.addBoard = function() { //create a new board. Issues a POST to /boards
    $scope.board.$save(function() {
      $state.go('boards'); // on success go back to the list i.e. boards state.
    });
  };
})
// .controller('LocationCreateController', function($scope, $state, $stateParams, LocationsFactory) {
//   $scope.location = new LocationsFactory();  //create new board instance. Properties will be set via ng-model on UI

//   $scope.addLocation = function() { //create a new board. Issues a POST to /boards
//     $scope.location.$save(function() {
//       $state.go('boards'); // on success go back to the list i.e. boards state.
//     });
//   };
// });
