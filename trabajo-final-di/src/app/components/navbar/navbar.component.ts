import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private gestorRutas:Router) { }

  ngOnInit(): void {
  }

  irHome(){
    this.gestorRutas.navigate(['home'])
  }
  irAsignaturas(){
    this.gestorRutas.navigate(['asignaturas'])
  }
  irCiclos(){
    this.gestorRutas.navigate(['ciclos'])
  }
  
  

}
