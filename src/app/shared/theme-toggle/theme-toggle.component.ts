import { Component, OnInit } from '@angular/core';
import { ThemeService, Theme } from '../../services/theme.service';

@Component({
  selector: 'app-theme-toggle',
  templateUrl: './theme-toggle.component.html',
  styleUrls: ['./theme-toggle.component.scss']
})
export class ThemeToggleComponent implements OnInit {
  currentTheme: Theme = 'dark';
  isDark = true;

  constructor(private themeService: ThemeService) { }

  ngOnInit(): void {
    // Subscribe to theme changes
    this.themeService.getTheme$().subscribe(theme => {
      this.currentTheme = theme;
      this.isDark = theme === 'dark';
    });
  }

  toggleTheme(): void {
    this.themeService.toggleTheme();
  }

  get themeIcon(): string {
    return this.isDark ? '☀️' : '🌙';
  }

  get themeLabel(): string {
    return this.isDark ? 'Light Mode' : 'Dark Mode';
  }
}
