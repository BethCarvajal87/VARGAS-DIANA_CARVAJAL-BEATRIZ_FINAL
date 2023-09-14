const uri = 'http://localhost:8080/turnos/';
const endPointRegistrar =  `${uri}registrar/`;
const datos = capturaDatos();
const formularioTurnos = document.getElementById("formularioTurnos"); 


function listarTurnos() {
    fetch(URL)
        .then(res => res.json())
        .then(data => {
            const table = document.querySelector('table');
            const rowExample = document.querySelector('.turno-row');
    
            // Itera a través de los pacientes y agrega una fila para cada uno
            data.forEach(turno => {
                const newRow = rowExample.cloneNode(true); // Clona la fila de ejemplo
    
                // Llena las celdas con los datos del paciente
                newRow.querySelector('.id').textContent = turno.id;
                newRow.querySelector('.idPaciente').textContent = turno.idPaciente;
                newRow.querySelector('.idOdontologo').textContent = turno.idOdontologo;
                newRow.querySelector('.idOdontologo').textContent = turno.idOdontologo;
    
                // Muestra la fila agregada en la tabla
                table.appendChild(newRow);
    
                // Muestra la fila clonada estableciendo su estilo "display" en "table-row"
                newRow.style.display = 'table-row';
            });
        })
        .catch(error => console.error(error));
    }
