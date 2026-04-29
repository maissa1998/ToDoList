import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TaskModel } from '../../Models/TaskModel';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  private apiUrl = `${environment.apiUrl}/tasks`;

  constructor(private http: HttpClient) {}

  createTask(task: TaskModel): Observable<TaskModel> {
  return this.http.post<TaskModel>(this.apiUrl, task, { withCredentials: true });
}

getMyTasks(): Observable<TaskModel[]> {
  return this.http.get<TaskModel[]>(`${this.apiUrl}/my-tasks`, {
    withCredentials: true
  });
}

toggleTask(task: TaskModel): Observable<TaskModel> {
  return this.http.put<TaskModel>(
    `${environment.apiUrl}/tasks/${task.id}/toggle`,
    {}, // empty body
    { withCredentials: true } // <-- correctly as options
  );
}

deleteTask(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/${id}`, { withCredentials: true });
}
}
