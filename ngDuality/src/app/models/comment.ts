export class Comment {
  id: number;
  createDate: string;
  body: string;
  active: boolean;
  flagged: boolean;
  creator: User;
  post: Post;


  constructor(
      id: number = 0,
      createDate: string = '',
      body: string = '',
      active: boolean = false,
      flagged: boolean = false,
      creator: User,
      post: Post

  ) {
      this.id = id;
      this.createDate = createDate;
      this.body = body;
      this.active = active;
      this.flagged = flagged;
      this.creator = User;
      this.post = Post;

  }
}


