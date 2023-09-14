const uri = 'http://localhost:8080/pacientes/';
const endPointRegistrar =  `${uri}registrar/`;
const datos = capturaDatos();
const formularioPacientes = document.getElementById("formularioPacientes"); 


function listarPacientes() {

    fetch(uri)
        .then(res => res.json())
        .then(data => {
            const table = document.querySelector('table');
            const rowExample = document.querySelector('.patient-row');

            // Itera a través de los pacientes y agrega una fila para cada uno
            data.forEach(paciente => {
                const newRow = rowExample.cloneNode(true); // Clona la fila de ejemplo

                // Llena las celdas con los datos del paciente
                newRow.querySelector('.id').textContent = paciente.id;
                newRow.querySelector('.nombre').textContent = paciente.nombre;
                newRow.querySelector('.apellido').textContent = paciente.apellido;
                newRow.querySelector('.dni').textContent = paciente.dni;
                newRow.querySelector('.fechaIngreso').textContent = paciente.fechaIngreso;

                // Llena la celda de domicilio con los datos del domicilio del paciente
                const domicilioObj = paciente.domicilio;
                newRow.querySelector('.domicilio').textContent = `${domicilioObj.calle}, ${domicilioObj.numero}, ${domicilioObj.localidad}, ${domicilioObj.provincia}`;

                // Muestra la fila agregada en la tabla
                table.appendChild(newRow);

                // Muestra la fila clonada estableciendo su estilo "display" en "table-row"
                newRow.style.display = 'table-row';
            });
        })
        .catch(error => console.error(error));
}



formularioPacientes.addEventListener("submit", function (e) {
    e.preventDefault();
    const datos = capturaDatos();
    registrarPacientes(datos); // Pasamos los datos como argumento a registrarPacientes
});

function registrarPacientes(datos) {
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
    const pacienteData = {
        nombre: document.getElementById('nombre').value,
        apellido: document.getElementById('apellido').value,
        dni: parseInt(document.getElementById('dni').value),
        fechaIngreso: document.getElementById('fechaIngreso').value,
        domicilio: {
            calle: document.getElementById('calle').value,
            numero: parseInt(document.getElementById('numero').value),
            localidad: document.getElementById('localidad').value,
            provincia: document.getElementById('provincia').value,
        }
    };
    return pacienteData;
}

function renderizarRespuesta(respuesta) {
    const respuestaDiv = document.querySelector(".respuesta"); // Selecciona el elemento donde mostrar la respuesta

    // Crea un elemento de lista no ordenada (ul) para mostrar los datos
    const ul = document.createElement("ul");

    // Itera a través de los datos de respuesta y crea elementos de lista (li) para cada campo
    for (const key in respuesta) {
        const li = document.createElement("li");
        li.textContent = `${key}: ${respuesta[key]}`;
        ul.appendChild(li);
    }

    // Limpia cualquier contenido anterior y agrega la nueva lista al div de respuesta
    respuestaDiv.innerHTML = "";
    respuestaDiv.appendChild(ul);
}

function limpiarFormulario() {
    document.getElementById('nombre').value = '';
    document.getElementById('apellido').value = '';
    document.getElementById('dni').value = '';
    document.getElementById('fechaIngreso').value = '';
    document.getElementById('calle').value = '';
    document.getElementById('numero').value = '';
    document.getElementById('localidad').value = '';
    document.getElementById('provincia').value = '';
}

