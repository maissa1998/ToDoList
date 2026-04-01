import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../Services/UserService/user-service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  username: string = '';
  password: string = '';
  error: string = '';
  
  constructor(private authService: UserService, private router: Router) {}

  login() {
    this.authService.login({ username: this.username, password: this.password }).subscribe({
      next: (res) => {
        localStorage.setItem('token', res.token);
        this.router.navigate(['/login']); 
      },
      error: (err) => {
        this.error = err.error || 'Login failed';
      }
    });
  }

  goToSignup() {
    this.router.navigate(['/signup']);
  }
}
