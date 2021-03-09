window.onload = function cargar(){
	var btn = document.getElementById("btn-logout");
	var btnC = document.getElementById("config-perfil");
	var btnE = document.getElementById("eliminar-perfil");
	var divnick = document.getElementById("nick");
    var divconfig = document.getElementById("cont-form-config");
	var usuario;
	var nombre;
	var apellido;
    var correo;
    var telefono;
    var pais;
    var postal;
    var sexo;
    
    //obtener datos

	
	fetch('https://practica-web2-2021a.herokuapp.com/Manejadorusuario',{
        method: 'POST',
    })
    .then(response => response.json())
    .then(data => {
    	
    	usuario = data.usuario
    	nombre = data.nombre
    	apellido = data.apellido
        correo = data.correo
        telefono = data.telefono
        pais = data.pais
        postal = data.postal
        sexo = data.sexo
        if(pais == "null"){
        	pais= "Omitir"
        }
    	if(postal == "null"){
        	postal= "Omitir"
        }
    	if(sexo == "null"){
        	sexo= "o"
        }
    	
    	if(data.status == 200){
    		
    		divnick.innerHTML = `
    		
    		<p>Bienvenido ${usuario}</p>
    		
            `
    	
    	} else {
    		
    		alert(data.message);
    		window.location.href = data.redirect;
    		
    	}
    	
    })



    //DESCONECTAR usuario

	
	btn.onclick = function logout(){
		fetch('https://practica-web2-2021a.herokuapp.com/Manejadorusuario',{
	        method: 'GET',
	    })
	    .then(response => response.json())
	    .then(data => {
	    	
	    	alert(data.message);
	    	window.location.href = data.redirect;
	    	
	    })
    }
	
	//ELIMINAR usuario
	
	btnE.onclick = function eliminarPerfil(){
		fetch('https://practica-web2-2021a.herokuapp.com/Manejadorusuario',{
	        method: 'DELETE',
	    })
	    .then(response => response.json())
	    .then(data => {
	    	
	    	alert(data.message);
	    	window.location.href = data.redirect;
	    	
	    })
	}
    

    //MODIFICAR DATOS de usuario

	btnC.onclick = function ConfigPerfil(){
		
		divconfig.innerHTML = `
		
		<div id="modif" class="cont-form-c">
            <div class="cont-frm">
                <h1>Actualizar perfil</h1>
                <form class="form" id="form-a">
                   <div id="form-content">
                   
                   <div class="form-part">
                       <label for="usuarioActualizado">Usuario</label>
                       <input type="text" name="usuarioActualizado" value="${usuario}" maxlength="19">
                       <label for="nombreActualizado">Nombre</label>
                       <input type="text" name="nombreActualizado" value="${nombre}" maxlength="19">
                       <label for="apellidoActualizado">Apellido</label>
                       <input type="email" name="apellidoActualizado" value="${apellido}" maxlength="19">
                   </div>
                   <div class="form-part">
                       <label for="correoActualizado">Correo</label>
                       <input type="text" name="correoActualizado" value="${correo}" maxlength="49">
                       <label for="telfActualizado">Telefono</label>
                       <input type="text" name="telfActualizado" value="${telefono}" pattern="[0-9]{4}-[0-9]{7}" title="ej 0424-1234567">
                   </div>
                   <div class="form-part">
                       <label for="pais">Pais</label>
                       <input type="text" name="pais" value="${pais}" maxlength="59">
                       <label for="postal">Codigo Postal</label>
                       <input type="text" name="postal" value="${postal}" maxlength="14">
                       <label for="sexo">Sexo M(masculino), F(femenino), O(omitir)</label>
                       <input type="text" name="sexo" value="${sexo}" maxlength="1">
                   </div>
                      
                   
                   </div>
                   
                   
                   
                   
                </form>
            </div>
            <div class="cont-b">
                <button class="boton" id="boton-actualizar">ACTUALIZAR</button>
                
                <button class="boton" id="boton-cancelar">CANCELAR</button>
                
            </div>
        </div>
        
        `
        var formConfig = document.getElementById("form-a");
		var divmodif = document.getElementById("modif")
        var btnActualizar = document.getElementById("boton-actualizar");
        var btnCancelar = document.getElementById("boton-cancelar");


        btnActualizar.onclick = function ActualizarPerfil() {
            var formconfig = new FormData(formConfig);
            var expresion = /^[a-z][\w.-]+@\w[\w.-]+\.[\w.-]*[a-z][a-z]$/i;
            var expresion1 = /^([0-9]{4})+(-)+([0-9]{7})$/i;
            var expresion2 = /^[mMfFoO]$/i;
            var expresion3 = /^[a-z|A-Z]{2,59}$/i;
            if (formconfig.get("usuarioActualizado") != "" && formconfig.get("nombreActualizado") != "" &&
            	formconfig.get("apellidoActualizado") != "" && formconfig.get("correoActualizado")  != "" && 
            	expresion.test(formconfig.get("correoActualizado")) && expresion1.test(formconfig.get("telfActualizado")) && 
            	expresion2.test(formconfig.get("sexo")) && expresion3.test(formconfig.get("pais"))){
            	
                fetch('https://practica-web2-2021a.herokuapp.com/Manejadorusuario',{
                    method: 'PUT',
                    body: formconfig
                    })
                .then(response => response.json())
                .then(data => {
                    alert(data.message);
                    window.location.href = data.redirect;
                })
            } else {
            	alert("No ha ingresado los valores correctamente... ")
            
            }
        }
        
        btnCancelar.onclick = function CancelarDatos() {
        	divconfig.removeChild(divmodif);
        }
		
		
	}
}