window.onload = function cargar() {
  var formRegistro = document.getElementById("form-r");
  var btn = document.getElementById("boton-registrar");


  btn.onclick = function Registrar(e) {
	e.preventDefault()
    var form = new FormData(formRegistro);
    var expresion = /^[a-z][\w.-]+@\w[\w.-]+\.[\w.-]*[a-z][a-z]$/i;
    var expresion1 = /^([0-9]{4})+(-)+([0-9]{7})$/i;
    if (form.get("usuario") != "" && form.get("nombre") != "" &&
    	form.get("apellido") != "" && form.get("pw") != "" &&
    	form.get("correo")  != "" && expresion.test(form.get("correo")) && 
    	form.get("pw").length > 8 && 
    	expresion1.test(form.get("telefono")) ){

    	//'https://practica-web2-2021a.herokuapp.com/Registro'
    	//'http://localhost:9090/Web2_practica_2021A/Registro'
        fetch('https://practica-web2-2021a.herokuapp.com/Registro',{
          method: 'POST',
          body: form
        })
        .then(response => response.json())
        .then(data => {
          alert(data.message);
        })
    }  else {
    	alert("Porfavor completar los campos correctamente")
    }  
  }
}
