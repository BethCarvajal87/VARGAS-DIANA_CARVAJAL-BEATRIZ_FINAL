const uri = 'http://localhost:8080/odontologos/';
const endPointRegistrar =  `${uri}registrar/`;
const datos = capturaDatos();
const formularioOdontologos = document.getElementById("formularioOdontologos"); 

function listarOdontologos() {
fetch(uri)
    .then(res => res.json())
    .then(data => {
        const table = document.querySelector('table');
        const rowExample = document.querySelector('.odontologo-row');

        // Itera a través de los pacientes y agrega una fila para cada uno
        data.forEach(odontologo => {
            const newRow = rowExample.cloneNode(true); // Clona la fila de ejemplo

            // Llena las celdas con los datos del paciente
            newRow.querySelector('.id').textContent = odontologo.id;
            newRow.querySelector('.matricula').textContent = odontologo.matricula;
            newRow.querySelector('.nombre').textContent = odontologo.nombre;
            newRow.querySelector('.apellido').textContent = odontologo.apellido;

            // Muestra la fila agregada en la tabla
            table.appendChild(newRow);

            // Muestra la fila clonada estableciendo su estilo "display" en "table-row"
            newRow.style.display = 'table-row';
        });
    })
    .catch(error => console.error(error));
}




formularioOdontologos.addEventListener("submit", function (e) {
    e.preventDefault();
    const datos = capturaDatos();
    registrarOdontologos(datos); // Pasamos los datos como argumento a registrarPacientes
});

function registrarOdontologos(datos) {
    const payload = {
        method: 'POST',
        body: JSON.stringify(datos),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    }
    fetch(endPointRegistrar, payload)
        .then(response => {
            if (!response.ok) return Promise.reject(response)
            return response.json()
        })
        .then(objetoDeDatos => { // Cambia "datos" a "objetoDeDatos"
            console.log(objetoDeDatos);
            renderizarRespuesta(objetoDeDatos);
            limpiarFormulario();
        })
        .catch(e => console.log(e))
    }

    function capturaDatos() {
        const odontologoData = {
            matricula: document.getElementById('matricula').value,
            nombre: document.getElementById('nombre').value,
            apellido: document.getElementById('apellido').value,           
        };
        return odontologoData;
    }

    function renderizarRespuesta(respuesta) {
        const respuestaDiv = document.querySelector(".respuesta"); // Selecciona el elemento donde mostrar la respuesta
    
        const template = `
        <p>✅ Odontologo creado con exito</p>         
    `;

        
    }

    function limpiarFormulario() {
        document.getElementById('matricula').value = '';
        document.getElementById('nombre').value = '';
        document.getElementById('apellido').value = '';
    }
    



