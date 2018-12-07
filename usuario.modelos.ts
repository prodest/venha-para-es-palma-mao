'use strict';
import {Entity, PrimaryGeneratedColumn, Column, OneToMany, BaseEntity} from "typeorm";
import {Tarefa} from "./tarefas.modelos";

@Entity()
export class Usuario extends BaseEntity{

    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    nome: string;

    @Column()
    cpf: string;

    @OneToMany(type => Tarefa, tarefas  => tarefas.id)
    tarefas: Tarefa[];

}