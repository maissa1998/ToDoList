import {TaskModel } from "./TaskModel";


export interface User {
  id?: number;
  username: string;
  password?: string; 
  email?: string;
  tasks?: TaskModel[];
}