//-- Consulta/nuevo --
//Extrae e incorpora doctor al form
$(".btn_addDoctor").on("click", function() {
    const id_usuario =$(this).closest('tr').children()[0].textContent;
    const id_personal =$(this).closest('tr').children()[1].textContent;
    const nombre = $(this).closest('tr').children()[3].textContent;
    $("#inputNameDoctor").val(nombre);
    $("#inputIdPersonal").val(id_personal);
 });

//-- Consulta/detalle --
//Extrae e incorpora una consulta al form
$(".btn_viewConsulta").on("click", function() {
    const id_consulta = $(this).closest('tr').children()[0].textContent;
    const id_paciente = $(this).closest('tr').children()[1].textContent;
    const id_personal = $(this).closest('tr').children()[2].textContent;
    const nota = $(this).closest('tr').children()[3].textContent;
    const estado = $(this).closest('tr').children()[4].textContent.trim();
    const fecha_atencion = $(this).closest('tr').children()[5].textContent;
    $("#inputIdConsulta").val(id_consulta);
    $("#inputIdPersonal").val(id_personal);
    $("#inputIdPaciente").val(id_paciente);
    $("#inputFechaAtencion").val(fecha_atencion);
    if(estado == "Atendido"){
        //alert("entre atendido");
        $("#inputNota").val(nota);
        //$("#btn_addConsulta").hide();
    }else{
        //alert("entre a no atendido");
        $("#inputNota").val("");
       // $("#btn_addConsulta").show();
    }
});

// -- index --
// Abrir modal de bienvenida inicio
//$('#myModal').on('shown.bs.modal', function () {
//    $('#myInput').trigger('focus')
//})


$(document ).ready(function() {
    $('#myModal').modal('toggle')
});

function checkRut(rut) {
    // Despejar Puntos
    var valor = rut.value.replace('.','');
    // Despejar Guión
    valor = valor.replace('-','');

    // Aislar Cuerpo y Dígito Verificador
    cuerpo = valor.slice(0,-1);
    dv = valor.slice(-1).toUpperCase();

    // Formatear RUN
    rut.value = cuerpo + '-'+ dv

    // Si no cumple con el mínimo ej. (n.nnn.nnn)
    if(cuerpo.length < 7) { rut.setCustomValidity("RUT Incompleto"); return false;}

    // Calcular Dígito Verificador
    suma = 0;
    multiplo = 2;

    // Para cada dígito del Cuerpo
    for(i=1;i<=cuerpo.length;i++) {

        // Obtener su Producto con el Múltiplo Correspondiente
        index = multiplo * valor.charAt(cuerpo.length - i);

        // Sumar al Contador General
        suma = suma + index;

        // Consolidar Múltiplo dentro del rango [2,7]
        if(multiplo < 7) { multiplo = multiplo + 1; } else { multiplo = 2; }

    }

    // Calcular Dígito Verificador en base al Módulo 11
    dvEsperado = 11 - (suma % 11);

    // Casos Especiales (0 y K)
    dv = (dv == 'K')?10:dv;
    dv = (dv == 0)?11:dv;

    // Validar que el Cuerpo coincide con su Dígito Verificador
    if(dvEsperado != dv) { rut.setCustomValidity("RUT Inválido"); return false; }

    // Si todo sale bien, eliminar errores (decretar que es válido)
    rut.setCustomValidity('');
}
function validateRut(rut){
    var Fn = {
        // Valida el rut con su cadena completa "XXXXXXXX-X"
        validaRut : function (rutCompleto) {
            rutCompleto = rutCompleto.replace("‐","-");
            if (!/^[0-9]+[-|‐]{1}[0-9kK]{1}$/.test( rutCompleto ))
                return false;
            var tmp 	= rutCompleto.split('-');
            var digv	= tmp[1];
            var rut 	= tmp[0];
            if ( digv == 'K' ) digv = 'k' ;

            return (Fn.dv(rut) == digv );
        },
        dv : function(T){
            var M=0,S=1;
            for(;T;T=Math.floor(T/10))
                S=(S+T%10*(9-M++%6))%11;
            return S?S-1:'k';
        }
    }

    $("#btnvalida").click(function(){
        if (Fn.validaRut( $("#txt_rut").val() )){
            $("#msgerror").html("El rut ingresado es válido :D");
        } else {
            $("#msgerror").html("El Rut no es válido :'( ");
        }
    });
}
