window.onload = function cargar() {
  var formInicioS = document.getElementById("form-is");
  var btn = document.getElementById("boton");


  btn.onclick = function login() {
    var form = new FormData(formInicioS);
    if (form.get("usuario") != "" &&   form.get("pw") != ""){
    	
        fetch('https://practica-web2-2021a.herokuapp.com/Login',{
          method: 'POST',
          body: form
        })
        .then(response => response.json())
        .then(data => {
      	  
      	 if(data.redirect == "#"){
          	alert(data.message);
          } else {
          	alert(data.message);
          	window.location.href = data.redirect;
          }
        })
    } else {
    	
    	alert("Por favor llenar los datos requeridos")
      
    }
  }
}