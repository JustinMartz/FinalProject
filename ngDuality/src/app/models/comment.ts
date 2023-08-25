import { Post } from './post';
import { User } from './user';

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
    creator: User = new User(),
    post: Post = new Post()
  ) {
    this.id = id;
    this.createDate = createDate;
    this.body = body;
    this.active = active;
    this.flagged = flagged;
    this.creator = creator;
    this.post = post;
  }
}
