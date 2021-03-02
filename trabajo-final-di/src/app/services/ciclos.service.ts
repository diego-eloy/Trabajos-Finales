import { Ciclo } from './../utils/Ciclo';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CiclosService {

  constructor() { }

  listaCiclos:any[] = [
    {
    nombre: 'DAM',
    curso: 1,
    asignaturas: [],
    imagen: 'assets/images/dam.jpeg',
  },
  {
    nombre: 'DAM',
    curso: 2,
    asignaturas: [],
    imagen: 'assets/images/dam.jpeg',
  },
  {
    nombre: 'DAW',
    curso: 1,
    asignaturas: [],
    imagen: 'assets/images/daw.jpeg',
  },
  {
    nombre: 'DAW',
    curso: 2,
    asignaturas: [],
    imagen: 'assets/images/daw.jpeg',
  }
  ];

  listaCiclo:Ciclo[] = [];


  getListaCiclos(): Ciclo[]{
    return this.listaCiclos;
  }

  getCicloDetalle(number:number,nombre:string):Ciclo[]{
    this.listaCiclos.forEach((item)=>{
      if (item.curso == number && item.nombre.includes(nombre)) {
        this.listaCiclo.push(item)
      }
    })
    
    return this.listaCiclo
  }


}
