import { Conocimiento } from './Conocimiento';
export interface Asignatura{
    nombre:string;
    profesor:string;
    conocimientos: Conocimiento[];
    ciclo:string;
    curso:number;
}