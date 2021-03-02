import { CiclosService } from './../../services/ciclos.service';
import { Ciclo } from './../../utils/Ciclo';
import { AsignaturasService } from './../../services/asignaturas.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Asignatura } from 'src/app/utils/Asignatura';

@Component({
  selector: 'app-detalle-ciclo',
  templateUrl: './detalle-ciclo.component.html',
  styleUrls: ['./detalle-ciclo.component.css']
})
export class DetalleCicloComponent implements OnInit {

  curso:number;
  nombre:string;


  listaDetalleAsignaturas:Asignatura[]= [];
  listaConocimientosAsignaturas:Asignatura[]= [];
  listaCiclos:Ciclo[] = [];
  listaAsingaturas:Asignatura[] = [];

  constructor(private gestorRutas:Router,private gestorRutasActivas:ActivatedRoute,private servicioAsignaturas:AsignaturasService,private servicioCiclos:CiclosService) { }

  ngOnInit(): void {
    this.gestorRutasActivas.paramMap.subscribe(param =>{
      this.curso = parseInt(param.get('number'));
      this.nombre = (param.get('nombre'));
      this.listaCiclos =this.servicioCiclos.getCicloDetalle(this.curso,this.nombre);
      this.listaDetalleAsignaturas = this.servicioAsignaturas.getCicloDetalle(this.curso,this.nombre);
    
      
    })
  }

}
