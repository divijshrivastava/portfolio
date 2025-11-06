import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

export type Theme = 'light' | 'dark';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  private readonly THEME_STORAGE_KEY = 'portfolio-theme';
  private currentTheme$ = new BehaviorSubject<Theme>(this.getInitialTheme());

  constructor() {
    // Apply the initial theme
    this.applyTheme(this.currentTheme$.value);
  }

  /**
   * Get the current theme as an observable
   */
  public getTheme$(): Observable<Theme> {
    return this.currentTheme$.asObservable();
  }

  /**
   * Get the current theme value
   */
  public getCurrentTheme(): Theme {
    return this.currentTheme$.value;
  }

  /**
   * Toggle between light and dark themes
   */
  public toggleTheme(): void {
    const newTheme: Theme = this.currentTheme$.value === 'light' ? 'dark' : 'light';
    this.setTheme(newTheme);
  }

  /**
   * Set a specific theme
   */
  public setTheme(theme: Theme): void {
    this.currentTheme$.next(theme);
    this.applyTheme(theme);
    this.saveTheme(theme);
  }

  /**
   * Apply the theme to the document
   */
  private applyTheme(theme: Theme): void {
    const htmlElement = document.documentElement;

    // Remove existing theme classes
    htmlElement.classList.remove('light-theme', 'dark-theme');

    // Add new theme class
    htmlElement.classList.add(`${theme}-theme`);

    // Set data attribute for CSS selector
    htmlElement.setAttribute('data-theme', theme);
  }

  /**
   * Get the initial theme from localStorage or system preference
   */
  private getInitialTheme(): Theme {
    // First, check localStorage
    const savedTheme = localStorage.getItem(this.THEME_STORAGE_KEY);
    if (savedTheme === 'light' || savedTheme === 'dark') {
      return savedTheme;
    }

    // Fall back to system preference
    if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
      return 'dark';
    }

    // Default to dark theme (current theme)
    return 'dark';
  }

  /**
   * Save the theme preference to localStorage
   */
  private saveTheme(theme: Theme): void {
    localStorage.setItem(this.THEME_STORAGE_KEY, theme);
  }

  /**
   * Check if the current theme is dark
   */
  public isDarkMode(): boolean {
    return this.currentTheme$.value === 'dark';
  }

  /**
   * Check if the current theme is light
   */
  public isLightMode(): boolean {
    return this.currentTheme$.value === 'light';
  }
}
