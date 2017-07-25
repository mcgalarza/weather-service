angular.module('app.services', []).factory('Board', function($resource) {
  return $resource('/boards/:id', { id: '@id' }, {
    update: {
      method: 'PUT'
    }
  });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});

angular.module('app.services').factory('Location', function($resource) {
	  return $resource('/boards/:name/:id'); // Note the full endpoint address
});