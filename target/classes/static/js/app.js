var myApp=angular.module("myApp",[]);

myApp.controller("myController",function($scope){

	console.log("In myController...");

	$scope.newUser={};
	$scope.clickedUser={};
	$scope.message = "";

	$scope.users=[
		{username:"rakoto", fullName:"Nirina", email:"rakoto@gmail.com"},
		{username:"andria", fullName:"Nambinina", email:"andria@gmail.com"},
		{username:"ranoro", fullName:"bery", email:"ranoro@gmail.com"}
	];

	$scope.saveUser=function(){
		//save dans la requete de rest
		$scope.users.push($scope.newUser);
		$scope.newUser={};
		$scope.message="New user Added Successfully!";

	};

	$scope.selectUser = function(user){
		console.log(user);
		$scope.clickedUser = user;
	};

	$scope.updateUser=function(){
		$scope.message="User updated Successfully!";
	};

	$scope.deleteUser = function(){
		$scope.users.splice($scope.users.indexOf($scope.clickedUser), 1);
		$scope.message=" User Deleted Successfully!";
	};
	$scope.clearMessage = function(){
		$scope.message="";
	};
});