import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../Services/UserService/user-service';
import { User } from '../../Models/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './signup.html',
  styleUrl: './signup.css',
})
export class Signup {

  user: User = {
    username: '',
    email: '',
    password: ''
  };

  confirmPassword: string = '';
  error: string = '';

  constructor(private router: Router) {}

  signup() {
    if (this.user.password !== this.confirmPassword) {
      this.error = "Passwords do not match";
      return;
    }
    console.log("User to register:", this.user);

    // Example success
    this.router.navigate(['/login']);
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }
}
