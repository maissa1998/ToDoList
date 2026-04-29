import { Routes } from '@angular/router';
import { Login } from './Auth/login/login';
import { Signup } from './Auth/signup/signup';
import { Task } from './TaskManagment/task/task';

export const routes: Routes = [
    { path: 'login', component: Login },
    { path: 'signup', component: Signup },
    {path:'task', component:Task},
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: '**', redirectTo: 'login' },
    
];
