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

  constructor(private authService: UserService, private router: Router) { }

  login() {
    this.authService.login({
      username: this.username,
      password: this.password
    }).subscribe({
      next: (res) => {
        console.log("Login success:", res);
        this.router.navigate(['/task']); 
      },
      error: (err) => {
        console.error(err);
        this.error = err.error || 'Invalid username or password';
      }
    });
  }

  goToSignup() {
    this.router.navigate(['/signup']);
  }
}
