import { User } from "./User";

export interface Task {
  id?: number;
  title: string;
  description?: string;
  completed: boolean; 
  user?: User; 
}