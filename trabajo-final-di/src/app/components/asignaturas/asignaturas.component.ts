import { element } from 'protractor';
import { AsignaturasService } from './../../services/asignaturas.service';
import { RouterModule } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Asignatura } from 'src/app/utils/Asignatura';

@Component({
  selector: 'app-asignaturas',
  templateUrl: './asignaturas.component.html',
  styleUrls: ['./asignaturas.component.css']
})
export class AsignaturasComponent implements OnInit {


  listaAsignaturas:Asignatura[] = []

  constructor(private gestorRutas:RouterModule,private servicioAsignaturas:AsignaturasService) { }

  


  ngOnInit(): void {
    this.listaAsignaturas = this.servicioAsignaturas.getListaAsignaturas();
    
  }

  pulsarBoton(arg1:string,arg2:string,arg3:string[]){
      if (arg1.length > 0 ) {
        this.listaAsignaturas.filter((item) => item.nombre)
      }else if (arg2.length >0) {
        this.listaAsignaturas.filter((item) => item.ciclo)
      }else if (arg3.length > 0) {
        this.listaAsignaturas.filter((item) => item.conocimientos.filter(element=>element.nombre))
      }
      }
      
  }


