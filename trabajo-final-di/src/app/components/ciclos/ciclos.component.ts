import { Asignatura } from './../../utils/Asignatura';
import { Ciclo } from './../../utils/Ciclo';
import { AsignaturasService } from './../../services/asignaturas.service';
import { CiclosService } from './../../services/ciclos.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-ciclos',
  templateUrl: './ciclos.component.html',
  styleUrls: ['./ciclos.component.css']
})
export class CiclosComponent implements OnInit {

  elementosCiclo: Ciclo[] = [];

  listaAsignaturasDaw: Asignatura[] = [];
  listaAsignaturasDam: Asignatura[] = [];
 

  constructor(private gestorRutas: Router, private servicioCiclos: CiclosService, private servicioAsignaturas: AsignaturasService) { }

  ngOnInit(): void {
    this.elementosCiclo = this.servicioCiclos.getListaCiclos();
    this.listaAsignaturasDaw = this.servicioAsignaturas.getAsignaturasDaw();
    this.listaAsignaturasDam = this.servicioAsignaturas.getAsignaturasDam();

  }

  pulsarBoton(number:number,nombre:string){

    this.gestorRutas.navigate(['detalle',number,nombre])
      
  }
}
