
//add dependency ui-router
var myApp=angular.module("myApp",['ui.router'])

//manage route,config redémarer au démarrage de l'app
myApp.config(function($stateProvider,$urlRouterProvider){

	//idex
	$stateProvider.state('getLogedUser',{
		url:'/getLogedUser',
		templateUrl:'listMessage.html',
		controller:'getLogedUserController'
	});
	
	//message
	$stateProvider.state('message',{
		url:'/message',
		templateUrl:'listMessage.html',
		controller:'listMessageController'
	});
	

	$stateProvider.state('message/nouveauMessage',{
		url:'/message/nouveauMessage',
		templateUrl:'nouveauMessage.html',
		controller:'nouveauMessageController'
	}); 
	
	$stateProvider.state('message/deleteMessage/{id}',{
		url:'/message/deleteMessage/{id}',
		templateUrl:'listMessage.html',
		controller:'listMessageController'
	});
	
	$stateProvider.state('message/updateMessage/{id}',{
		url:'/message/updateMessage/{id}',
		templateUrl:'listMessage.html',
		controller:'listMessageController'
	});
	
	//clients
	$stateProvider.state('clients',{
		url:'/chercherClients',
		templateUrl:'clients.html',
		controller:'ClientController'
	});
	
	
	$stateProvider.state('clients/nouveauClient',{
		url:'/clients/nouveauClient',
		templateUrl:'clients.html',
		controller:'ClientController'
	});
	
	
	//profil - user
	$stateProvider.state('profil',{
		url:'/updateUser',
		templateUrl:'profil.html',
		controller:'ProfilController'
	});
	
	//planning
	$stateProvider.state('planning',{
		url:'/planning',
		templateUrl:'planning.html',
		controller:'PlanningController'	
	});
	//planning
	$stateProvider.state('agence/listAgence',{
		url:'agence/listAgence',
		templateUrl:'planning.html',
		controller:'PlanningController'	
	});
	/*$stateProvider.state('chercherUser',{
		url:'/chercherUser',
		templateUrl:'planning.html',
		controller:'PlanningController'	
	});*/
	
	$stateProvider.state('categorie/listCategorie',
			{
				url:'/categorie/listCategorie',
				templateUrl:'planning.html',
				controller:'PlanningController'
			});
});


//index
myApp.controller("IndexController", function($scope, $http) {
	
	
	
	$scope.dataUser=null;
	//getLogedUser
	$scope.getLogedUser=function(){
		//message-> lien en MessageRestService
		$http.get("getLogedUser")
		.success(function(data) {
			$scope.dataUser=data;
			$scope.dataUser=$scope.editU($scope.dataUser.username);
		});
	};
	$scope.getLogedUser();
	
	
	/*edit**/
	$scope.editU = function(username){
		console.log($scope.dataUser.username);
		$http.get("profil/editUser/"+$scope.dataUser.username)
			.success(function(response){
				$scope.dataUser=response;
			});
	};
	//nouveau message
	$scope.message={};
	$scope.errors=null;
	//$scope.mode={value:"form"}
	$scope.exception={message:null}
	$scope.saveMessage=function(){
		$http.post("message/nouveauMessage", $scope.message)
		.success(function(data) {
			if(!data.erros){
				$scope.message=data;
				$scope.errors=null;
				//$scope.mode.value="listform";
				$scope.message.title="";
				$scope.message.contenu="";
				$scope.listMessage();
			}
			else{
				$scope.errors=data;
			}
		})
		.error(function(data) {
			$scope.exception.message=data.message;
		});
	}; 
	
	//listMessage
		$scope.pageMessage=null;
		$scope.pageCourante=0;
		$scope.size=5;
		$scope.pages=[];
		 
		 
		$scope.listMessage=function(){
			//message-> lien en MessageRestService
			$http.get("message?page="+$scope.pageCourante+"&size="+$scope.size)
			.success(function(data) {
				$scope.pageMessage=data;
				$scope.pages=new Array(data.totalPages);
				$scope.mode.value="listform"
				
			});
		};
		
		//crée un évemenent pour appeller listMessage
		$scope.listMessage();
		
		$scope.gotoPage=function(p){
			$scope.pageCourante=p;
			$scope.listMessage();
			//refresh();
		}
		
		//delete 
		$scope.deleteM = function(id){
			
				$http.get("message/deleteMessage/"+id)
				  .then(function successCallback(response){
					$scope.pageMessage=response;
					$scope.listMessage();
				});
		};
		$scope.listMessage();
		
		//edit
		$scope.editM = function(id){
			console.log(id);
			$http.get("message/editMessage/"+id)
				.success(function(response){
					$scope.message=response;
					//$scope.message.title=response.title
				});
		};
		
		
		//update
		$scope.updateMessage = function(){
			console.log($scope.message.id);
			$http.put("message/updateMessage/"+$scope.message.id, $scope.message)
			.success(function(response){
				$scope.message=response;
				$scope.listMessage();
				//$scope.message.title=response.title
			});
			
		};
});


//message 
myApp.controller("listMessageController", function($scope,$http) {
	//list message

	
	$scope.dataUser=null;
	$scope.getLogedUser=function(){
		$http.get("getLogedUser")
		.success(function(data) {
			$scope.dataUser=data;
			$scope.dataUser=$scope.editU($scope.dataUser.username);
			//$scope.changeAgence("username"+$scope.dataUser.username);
		});
	};
	
	
	$scope.getLogedUser();
	
	/*edit**/
	$scope.editU = function(username){
		console.log($scope.dataUser.username);
		$http.get("profil/editUser/"+$scope.dataUser.username)
			.success(function(response){
				$scope.dataUser=response;
			});
	};
	
	
	
	//nouveau message
	$scope.message={};
	$scope.errors=null;
	$scope.mode={value:"form"}
	$scope.exception={message:null}
	$scope.saveMessage=function(){
		$http.post("message/nouveauMessage", $scope.message)
		.success(function(data) {
			if(!data.erros){
				$scope.message=data;
				$scope.errors=null;
				//$scope.mode.value="listform";
				$scope.message.title="";
				$scope.message.contenu="";
				$scope.listMessage();
			}
			else{
				$scope.errors=data;
			}
		})
		.error(function(data) {
			$scope.exception.message=data.message;
		});
	}; 
	
	//listMessage
		$scope.pageMessage=null;
		$scope.pageCourante=0;
		$scope.size=5;
		$scope.pages=[];
		 
		 
		$scope.listMessage=function(){
			//message-> lien en MessageRestService
			$http.get("message?page="+$scope.pageCourante+"&size="+$scope.size)
			.success(function(data) {
				$scope.pageMessage=data;
				$scope.pages=new Array(data.totalPages);
				$scope.mode.value="listform"
				
			});
		};
		
		//crée un évemenent pour appeller listMessage
		$scope.listMessage();
		
		$scope.gotoPage=function(p){
			$scope.pageCourante=p;
			$scope.listMessage();
			//refresh();
		}
		
		//delete 
		$scope.deleteM = function(id){
			$http.get("message/deleteMessage/"+id)
				.then(function successCallback(response){
					$scope.pageMessage=response;
					$scope.listMessage();
				});
		};
		$scope.listMessage();
		
		//edit
		$scope.editM = function(id){
			$http.get("message/editMessage/"+id)
				.success(function(response){
					$scope.message=response;
					//$scope.message.title=response.title
				});
		};
		
		$scope.updateMessage = function(){
			$http.put("message/updateMessage/"+$scope.message.id, $scope.message)
			.success(function(response){
				$scope.message=response;
				$scope.listMessage();
				//$scope.message.title=response.title
			});
			
		};
		$scope.listMessage();
		
});

myApp.controller("nouveauMessageController",function($scope,$http){
	$scope.message={};
	$scope.errors=null;
	$scope.mode={value:"form"}
	$scope.exception={message:null}
	$scope.saveMessage=function(){
		$http.post("message/nouveauMessage", $scope.message)
		.success(function(data) {
			if(!data.erros){
				$scope.message=data;
				$scope.errors=null;
				$scope.mode.value="confirm";
			}
			else{
				$scope.errors=data;
			}
		})
		.error(function(data) {
			$scope.exception.message=data.message;
		});
	}; 
});


//clients
myApp.controller("ClientController", function($scope,$http) {
	
	
	$scope.dataUser=null;
	//getLogedUser
	$scope.getLogedUser=function(){
		//message-> lien en MessageRestService
		$http.get("getLogedUser")
		.success(function(data) {
			$scope.dataUser=data;
			$scope.dataUser=$scope.editU($scope.dataUser.username);
		});
	};
	$scope.getLogedUser();
	
	
	/*edit**/
	$scope.editU = function(username){
		console.log($scope.dataUser.username);
		$http.get("profil/editUser/"+$scope.dataUser.username)
			.success(function(response){
				$scope.dataUser=response;
			});
	};
	
	
	$scope.pageCourante=0;
	$scope.size=5;
	$scope.pages=[];
	$scope.pageClients=null;
	
	//data binding, change key_word -> text
	$scope.key_word_nom="";
	$scope.key_word_telephone="";
	$scope.key_word_code_postale="";
	$scope.key_word_ville="";
	$scope.key_word_numero_client="";
	$scope.key_word_ref_client="";
	//$scope.key_word_numero_serie="";
	$scope.searchClient=function()
	{	
		$http.get("/chercherClients?key_word_nom="
				+$scope.key_word_nom
				+"&key_word_telephone="+$scope.key_word_telephone
				+"&key_word_code_postale="+$scope.key_word_code_postale
				+"&key_word_ville="+$scope.key_word_ville
				+"&key_word_numero_client="+$scope.key_word_numero_client
				+"&key_word_ref_client="+$scope.key_word_ref_client
				//+"&key_word_numero_serie="+$scope.key_word_numero_serie
				+"&page="+$scope.pageCourante+"&size="+$scope.size)
		.success(function(data) {
			$scope.pageClients=data;
			$scope.pages=new Array(data.totalPages);
		})
		.error(function(err) {
			console.log(err);
		});
				
		
	}
	
	$scope.searchClient();
	$scope.gotoPage=function(p){
		$scope.pageCourante=p;
		$scope.searchClient();
	};	
	
	
	$scope.viderClient=function(){
		$scope.key_word_nom="";
		$scope.key_word_telephone="";
		$scope.key_word_code_postale="";
		$scope.key_word_ville="";
		$scope.key_word_numero_client="";
		$scope.key_word_ref_client="";
	};
	
	
	$scope.client={};
	$scope.errors=null;
	//$scope.mode={value:"form"}
	$scope.exception={client:null}
	
	$scope.saveClient=function(){
		$http.post("clients/nouveauClient", $scope.client)
		.success(function(data) {
			$scope.client=data;
			/*if(!data.erros){
				$scope.client=data;
				$scope.errors=null;
				//$scope.mode.value="confirm";
			}
			else{
				$scope.errors=data;
			}*/
			$scope.searchClient();
		})
		
		.error(function(data) {
			$scope.exception.client=data.client;
		});
	}; 
});
/*myApp.controller("nouveauClientController",function($scope,$http){
	
	
});*/

//profil - user
myApp.controller("ProfilController", function($scope,$http) {
	
	$scope.dataUser=null;
	$scope.getLogedUser=function(){
		//message-> lien en MessageRestService
		$http.get("getLogedUser")
		.success(function(data) {
			$scope.dataUser=data;
			$scope.dataUser=$scope.editU($scope.dataUser.username);
		});
	
		
	};
	$scope.getLogedUser();
	
	/*edit**/
	$scope.editU = function(username){
		console.log($scope.dataUser.username);
		$http.get("profil/editUser/"+$scope.dataUser.username)
			.success(function(response){
				$scope.dataUser=response;
			});
	};

	//update
	$scope.updateUser = function(){
		console.log($scope.dataUser.username);
		$http.put("profil/updateUser/"+$scope.dataUser.username, $scope.dataUser)
		.success(function(response){
			$scope.dataUser=response;
			$scope.editU($scope.dataUser.username);
			//$scope.listMessage();
			//$scope.message.title=response.title
		});
		
	};
	/**departement user**/
	$scope.departement = null;
	$scope.gererDepartement = function()
	{
		$http.get("departement/listDepartement")
				.success(function(data){
					$scope.departement=data;
				})
				.error(function(err) {
					console.log(err);
					$scope.message_error = "accés réfusé";
				});
	};
	$scope.gererDepartement();
	
	$scope.dataUserAdd={};
	$scope.errors=null;
	$scope.exception={dataUserAdd:null}
	$scope.saveUser=function(){
		$http.post("user/nouveauUser", $scope.dataUserAdd)
		.success(function(data) {
			if(!data.erros){
				$scope.dataUserAdd=data;
				$scope.errors=null;
			}
			else{
				$scope.errors=data;
			}
		})
		.error(function(data) {
			$scope.exception.dataUserAdd=data.dataUserAdd;
		});
	}; 
	
	
	/***list ROLe***/
	
	$scope.roles = null;
	$scope.listRole = function()
	{
		$http.get("role/listRoles")
				.success(function(data){
					$scope.roles=data;
				})
				.error(function(err) {
					console.log(err);
					//$scope.message_error = "accés réfusé";
				});
	};
	$scope.listRole();
	
	
	/***sexe***/
 	
 	
 	$scope.sexes=[
		{id:1,name:'masculin'},
		{id:2,name:'feminin'}

	];
	
});

/*myApp.controller('DateController', ['$scope', function($scope) {
    $scope.date = {
            value: new Date()
     };
}]);
*/

//planning
myApp.controller("PlanningController", function($scope,$http) {
	
	/***date**/
	$scope.date = {
            value: new Date()
     };
	
	$scope.dateNew =  new Date();
	
	
	  
	$scope.message_error = "";
	$scope.dataUser=null;
	$scope.getLogedUser=function(){
		$http.get("getLogedUser")
		.success(function(data) {
			$scope.dataUser=data;
			$scope.dataUser=$scope.editU($scope.dataUser.username);
			//$scope.changeAgence("username"+$scope.dataUser.username);
		});
	};
	
	
	$scope.getLogedUser();
	
	/*edit**/
	$scope.editU = function(username){
		console.log($scope.dataUser.username);
		$http.get("profil/editUser/"+$scope.dataUser.username)
			.success(function(response){
				$scope.dataUser=response;
			});
	};
	
	/**departement***/
	$scope.departementUser=null;
	
	/**onglet intervention et agence**/
	$scope.mode={value:"confirm"}
	
	$scope.changeAgence = function(username){
		console.log("???"+username);
		$scope.mode.value="form";
		$http.get("planning/chercherDepartementUser?username="
				+username)
			.success(function(data){
				$scope.departementUser=data;
				console.log("r="+$scope.departementUser.departement.agence.nom_agence);
			});
	};
	$scope.changeIntervention = function(){
		$scope.mode.value="confirm";
	};
	
	
   /**departement user**/
	$scope.departement = null;
	$scope.gererDepartement = function()
	{
		$http.get("departement/listDepartement")
				.success(function(data){
					$scope.departement=data;
				})
				.error(function(err) {
					console.log(err);
					$scope.message_error = "accés réfusé";
				});
	};

	/***affiche list etatPlanning**/
	
	$scope.etatPlanning = null;
	$scope.listEtatPlanning = function()
	{
		$http.get("etat/listEtat")
				.success(function(data){
					$scope.etatPlanning=data;
				})
				.error(function(err) {
					console.log(err);
				});
	};
	$scope.listEtatPlanning();
	
	
	
	
	/***affiche list agence**/
	
	$scope.agence = null;
	$scope.listAgence = function()
	{
		$http.get("agence/listAgence")
				.success(function(data){
					$scope.agence=data;
				})
				.error(function(err) {
					console.log(err);
					//$scope.message_error = "accés réfusé";
				});
	};
	$scope.listAgence ();
	
/***affiche list client**/
	
	$scope.clients = null;
	$scope.listclients = function()
	{
		$http.get("client/listClient")
				.success(function(data){
					$scope.clients=data;
				})
				.error(function(err) {
					console.log(err);
					//$scope.message_error = "accés réfusé";
				});
	};
	$scope.listclients ();
	
	
	$scope.pagePlanningUser=null;
	$scope.affichetab={value:"non_affiche"}
	$scope.key_word_nom_client="";
	$scope.key_word_numero_client="";
	$scope.key_word_code_postale="";
	$scope.key_word_numero_intervention="";
	$scope.key_word_nom_etat="";
	
	$scope.rechercherUser=function(depart)
	{	
		var month = parseInt($scope.date.value.getMonth()+1);
		var monthString ="";
		if(month < 10)
			{
				monthString = "0"+ ($scope.date.value.getMonth() + 1);
			}
		else {
			monthString = ($scope.date.value.getMonth() + 1);
		}
		
		
			$http.get("planning/chercherUser?key_word_numero_intervention="
					+$scope.key_word_numero_intervention
					//key_word_nom_client
					+"&key_word_nom_client="+$scope.key_word_nom_client
					+"&key_word_code_postale="+$scope.key_word_code_postale
					+"&key_word_numero_client="+$scope.key_word_numero_client
					+"&key_word_nom_depart="+depart
					+"&key_word_nom_etat="+$scope.key_word_nom_etat
					+"&key_date_Inter="+$scope.date.value.getFullYear() + "-" + monthString + "-" + $scope.date.value.getDate()
					)
			.success(function(data) {

				console.log($scope.date.value.getFullYear() + "-" + monthString + "-" + $scope.date.value.getDate());
				
				console.log("tafiditra");
				console.log(monthString);
				$scope.pagePlanningUser=data;
				console.log("username="+$scope.pagePlanningUser);
				$scope.affichetab.value="affiche";
				$scope.listEvenement();
				//console.log(depart);
				
				
			})
			.error(function(err) {
				$scope.message_error = "accés réfusé";
			});
		
	
	};
	
	/**horaires**/
	$scope.Heures = null;
	$scope.listHeures = function()
	{
		$http.get("heure/listHeure")
				.success(function(data){
					$scope.Heures=data;
					
				})
				.error(function(err) {
					console.log(err);
				});
	};
	$scope.listHeures();
	
	/***chercher USER simple**/

	$scope.pagePlanningUserSimple=null;
	$scope.affichetab={value:"non_affiche"}
	$scope.key_word_nom_client="";
	$scope.key_word_numero_client="";
	$scope.key_word_code_postale="";
	$scope.key_word_numero_intervention="";
	$scope.key_word_nom_etat="";
	
	$scope.rechercherUserSimple=function(depart)
	{	
		
		//console.log("Month==="+$scope.date.value.getMonth());
		var month = parseInt($scope.date.value.getMonth()+1);
		var monthString ="";
		
		
		if(month < 10)
			{
				//console.log("inférieur à 10");
				monthString = "0"+ ($scope.date.value.getMonth() + 1);
				//console.log("mois="+monthString);
			}
		else {
			//console.log("Supérieur ou égal à 10");
			monthString = ($scope.date.value.getMonth() + 1);
			//console.log("mois"+monthString);
		}
		
		
			$http.get("planning/chercherUserSimple?key_word_numero_intervention="
					+$scope.key_word_numero_intervention
					//key_word_nom_client
					+"&key_word_nom_client="+$scope.key_word_nom_client
					+"&key_word_code_postale="+$scope.key_word_code_postale
					+"&key_word_numero_client="+$scope.key_word_numero_client
					+"&key_word_nom_depart="+depart
					+"&key_word_nom_etat="+$scope.key_word_nom_etat
					+"&key_date_Inter="+$scope.date.value.getFullYear() + "-" + monthString + "-" + $scope.date.value.getDate()
					)
			.success(function(data) {
				
				//console.log("formatdate:"+$scope.date.value);
				$scope.pagePlanningUserSimple=data;
				$scope.affichetab.value="affiche";
				
				date.getDate();
				//console.log("ddz=="+date.getDate());
				$scope.message_error = "créer vos événements";
				
				
				
			})
			.error(function(err) {
				//console.log("formatdate:"+$scope.date.value);
				//console.log("ddz=="+date.getDate());
				
				//console.log(err);
				$scope.message_error = "accés réfusé";
			});
		
		
	};

	
	/**recherche par département**/
	$scope.pagePlanningUserDepart=null;
	$scope.affichetab={value:"non_affiche"}
	$scope.rechercherUserDepart = function(depart){
		$http.get("planning/listUser?key_word_user_depart="
				+depart)
				.success(function(data) {
					$scope.affichetab.value="affiche";
					$scope.pagePlanningUserDepart=data;
					console.log(depart);
						
						
				})
				.error(function(err) {
					console.log(err);
					//$scope.affichetab.value="non_affiche";
					$scope.message_error = "accés réfusé";
				});
	};
	
	
	/***vider rechercher intervention ***/
	$scope.vider=function(){
		$scope.key_word_nom_client="";
		$scope.key_word_numero_client="";
		$scope.key_word_code_postale="";
		$scope.key_word_numero_intervention="";
		$scope.key_word_nom_etats="";
	};
	
	
	$scope.clients = null;
	$scope.listClient = function()
	{
		$http.get("client/listClient")
				.success(function(data){
					$scope.clients=data;
					console.log($scope.clients);
				})
				.error(function(err) {
					console.log(err);
				});
	};
	$scope.listClient();
	
	/****nouveau Planning ***/
	$scope.datedebut = new Date();
	
	$scope.nouveauPlanning={};
	$scope.errors=null;
	//$scope.mode={value:"form"}
	$scope.exception={nouveauPlanning:null}
	
	$scope.addPlanning=function(){
		
		
		console.log("heure="+$scope.nouveauPlanning.heure.code_heure);
		//datedebut
		/*var month = parseInt($scope.date.value.getMonth()+1);
		var monthString ="";
		
		
		if(month < 10)
			{
				monthString = "0"+ ($scope.date.value.getMonth() + 1);
			}
		else {
			monthString = ($scope.date.value.getMonth() + 1);
		}
		var date_tran =$scope.date.value.getFullYear() + "-" + monthString + "-" + $scope.date.value.getDate() 
		console.log("user"+$scope.nouveauPlanning.user.username);*/
		
		var month = parseInt($scope.datedebut.getMonth()+1);
		var monthString ="";
		
		
		if(month < 10)
			{
				monthString = "0"+ ($scope.datedebut.getMonth() + 1);
			}
		else {
			monthString = ($scope.datedebut.getMonth() + 1);
		}
		var date_tran =$scope.datedebut.getFullYear() + "-" + monthString + "-" + $scope.datedebut.getDate() 
		console.log("user"+$scope.nouveauPlanning.user.username);
		
		//planning/nouveauPlanning/{user}/{date}/{heure}/{client}
		$http.get("planning/nouveauPlanning/"+$scope.nouveauPlanning.user.username+"/"+date_tran+"/"+$scope.nouveauPlanning.heure.code_heure+"/"+$scope.nouveauPlanning.client.numero_client+"/"+$scope.nouveauPlanning.description)
		.success(function(data) {
			$scope.nouveauPlanning=data;
			$scope.datedebut = new Date();
		})
		
		.error(function(data) {
			$scope.exception.nouveauPlanning=data.nouveauPlanning;
		});
	};  
	
	
	//delete 
	$scope.pagePlanning=null;
	$scope.deletePlanning = function(id){
		$http.get("planning/deletePlanning/"+id)
			.then(function successCallback(response){
				$scope.pagePlanning=response;
			});
	};
	
	
	//edit
	$scope.editPlanning = function(id){
		console.log(id);
		$http.get("planning/editPlanning/"+id)
			.success(function(response){

				$scope.pagePlanning=response;
				console.log("dssdfsd="+$scope.pagePlanning.id_Intervention);
			});
	};
	
	//console.log("id=="+$scope.pagePlanning.id_Intervention);
	//update
	$scope.updatePlanning = function(){
		
		$http.put("planning/updatePlanning/"+$scope.pagePlanning.id_Intervention, $scope.pagePlanning)
		.success(function(response){
			console.log("tafiditra v?");
			$scope.pagePlanning=response;
			//$scope.rechercherUser();
			//$scope.message.title=response.title
		});
		
	};
	
	//update etat planning
	$scope.updateEtatPlanning = function(){
			
			$http.put("planning/updateEtatPlanning/"+$scope.pagePlanning.id_Intervention, $scope.pagePlanning)
			.success(function(response){
				
				$scope.pagePlanning=response;
			});
		
	};
	
	
	
	/**Fermer Planning***/
	
	$scope.planning={};
	$scope.errors=null;
	//$scope.mode={value:"form"}
	$scope.exception={planning:null}
	
	$scope.FermerCreneauPlanning=function(code_heure,code_user,date_planning){
		
		
		console.log("heure"+code_heure);
		console.log("user"+code_user);
		console.log("date"+date_planning);
		
		var month = parseInt(date_planning.getMonth()+1);
		var monthString ="";
		
		
		if(month < 10)
			{
				//console.log("inférieur à 10");
				monthString = "0"+ (date_planning.getMonth() + 1);
				//console.log("mois="+monthString);
			}
		else {
			//console.log("Supérieur ou égal à 10");
			monthString = (date_planning.getMonth() + 1);
			//console.log("mois"+monthString);
		}
		var date_tran =date_planning.getFullYear() + "-" + monthString + "-" + date_planning.getDate() 
		console.log("code_heure"+code_heure);
		//FermerCreneauPlanning(h.code_heure,u.user.username,date.value)
		
		$http.get("planning/planningFermer/"+code_user+"/"+code_heure+"/"+date_tran)
		.success(function(data) {
			$scope.planning=data;
		})
		
		.error(function(data) {
			$scope.exception.planning=data.planning;
		});
	};  

	
	/***fermer tous les créneaux***/
	
		$scope.FermerTousCreneauPlanning=function(code_user,date_planning){
			
			var month = parseInt(date_planning.getMonth()+1);
			var monthString ="";
			
			
			if(month < 10)
				{
					monthString = "0"+ (date_planning.getMonth() + 1);
				}
			else {
				monthString = (date_planning.getMonth() + 1);
			}
			var date_tran =date_planning.getFullYear() + "-" + monthString + "-" + date_planning.getDate() 
			
			$http.get("planning/planningFermerTousCreneau/"+code_user+"/"+date_tran)
			.success(function(data) {
				$scope.planning=data;
			})
			
			.error(function(data) {
				$scope.exception.client=data.client;
			});
		};  
	
		
		
		
		
	
	/*****début évenement****/
/***affiche list categorieEvenement**/
	
	$scope.categorieEvenement = null;
	$scope.listCategorieEvenement = function()
	{
		$http.get("categorie/listCategorie")
				.success(function(data){
					$scope.categorieEvenement=data;
					console.log("cat="+$scope.categorieEvenement);
				})
				.error(function(err) {
					console.log(err);
				});
	};
	$scope.listCategorieEvenement();

	

	
	
/***affiche list user**/
	
	$scope.users = null;
	$scope.listUsers = function()
	{
		$http.get("user/listUsers")
				.success(function(data){
					$scope.users=data;
					
				})
				.error(function(err) {
					console.log(err);
				});
	};
	$scope.listUsers();
	
	
	
	
	/*$scope.users=[
	      		{id:1,name:'james'},
	      		{id:2,name:'ethan'},
	      		{id:1,name:'rakoto'},
	      		{id:1,name:'ranaivo'}

	      	];*/
	      $scope.selected =[];
	      $scope.exist = function(item){
	      	return $scope.selected.indexOf(item) > -1;
	      }

	      $scope.toggleSelection = function(item){
	      	var idx = $scope.selected.indexOf(item);
	      	if(idx > -1){
	      		$scope.selected.splice(idx,1);
	      	}
	      	else{
	      		$scope.selected.push(item);
	      	}
	      }

	     $scope.checkAll = function(){
	      	if($scope.selectAll){
	      		angular.forEach($scope.users, function(item){
	      			idx = $scope.selected.indexOf(item);
	      			if(idx >= 0){
	      				return true;
	      			}
	      			else{
	      				$scope.selected.push(item);
	      			}
	      		})
	      	}
	      	else{
	      		$scope.selected = [];
	      	}
	      }
	

	    /***nouveau événement**/
	 	$scope.evenementDate = {
	 			
	            date_debut: new Date(),
	            date_fin: new Date()
	     };
	 	
	 	
	 	$scope.errors=null;
	 	$scope.evenement={};
	 	//$scope.selected={};
	 		
	 	$scope.saveEvenement=function(){
	 		
	 		var month_debut = parseInt($scope.evenementDate.date_debut.getMonth()+1);
			var monthString_debut ="";
			
			
			if(month_debut < 10)
				{
				monthString_debut = "0"+ ($scope.evenementDate.date_debut.getMonth() + 1);
				}
			else {
				monthString_debut = ($scope.evenementDate.date_debut.getMonth() + 1);
			}
			var date_tran_debut =$scope.evenementDate.date_debut.getFullYear() + "-" + monthString_debut + "-" + $scope.evenementDate.date_debut.getDate() 
			
			
			var month_fin = parseInt($scope.evenementDate.date_fin.getMonth()+1);
			var monthString_fin ="";
			
			
			if(month_fin < 10)
				{
				monthString_fin = "0"+ ($scope.evenementDate.date_fin.getMonth() + 1);
				}
			else {
				monthString_fin = ($scope.evenementDate.date_fin.getMonth() + 1);
			}
			var date_tran_fin =$scope.evenementDate.date_fin.getFullYear() + "-" + monthString_fin + "-" + $scope.evenementDate.date_fin.getDate() 
			
			console.log("type"+$scope.evenement.categorie.type_evenement);
	 		$http.get("evenement/nouveauEvenement/"+$scope.evenement.categorie.type_evenement+"/"+date_tran_debut+"/"+date_tran_fin)
	 		
	 		.success(function(data) {
	 			$scope.evenement=data;
	 			//console.log("add event ok");
	 		});
	 		
	 		/*.error(function(data) {
	 			$scope.exception.evenement=data.evenement;
	 		});*/
	 	};  
	 	
	 	$scope.evenements= null;
		$scope.listEvenement = function()
		{
			$http.get("evenement/listEvenement")
					.success(function(data){
						$scope.evenements=data;
					})
					.error(function(err) {
						console.log(err);
						$scope.message_error = "accés réfusé";
					});
		};
		$scope.listEvenement();

	
	
});
