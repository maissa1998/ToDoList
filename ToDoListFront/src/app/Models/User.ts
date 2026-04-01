import { Task } from "./Task";


export interface User {
  id?: number;
  username: string;
  password?: string; 
  email?: string;
  tasks?: Task[];
}