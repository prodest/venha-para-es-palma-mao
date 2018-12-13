'use strict';
import { Entity, PrimaryGeneratedColumn, Column, ManyToOne, BaseEntity } from "typeorm";
import { Usuario } from "./usuario.modelos";

@Entity()
export class Tarefa extends BaseEntity {

    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    nome: string;

    @Column()
    descricao: string;

    @ManyToOne( type => Usuario, usuario => usuario.id )
    usuario: Usuario;
}