export class UserContact {
  public fullName: string;
  public email: string;
  public message: string;

  constructor(userName: string, email: string, message: string) {
    this.fullName = userName;
    this.email = email;
    this.message = message;
  }
}
