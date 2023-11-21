export class UserContact {
  public fullName: string;
  public email: string;
  public message: string;
  public status: string | undefined;
  public ipAddress: string | undefined;
  public isResponded: string | undefined;
  public time: string | undefined;

  constructor(
    userName: string,
    email: string,
    message: string,
    status?: string,
    ipAddress?: string,
    isResponded?: string,
    time?: string,
  ) {
    this.fullName = userName;
    this.email = email;
    this.message = message;
    this.status = status;
    this.ipAddress = ipAddress;
    this.isResponded = isResponded;
    this.time = time;
  }
}
