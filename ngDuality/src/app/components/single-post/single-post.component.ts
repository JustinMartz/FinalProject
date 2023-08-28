import { PostService } from 'src/app/services/post.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Post } from 'src/app/models/post';

@Component({
  selector: 'app-single-post',
  templateUrl: './single-post.component.html',
  styleUrls: ['./single-post.component.css']
})
export class SinglePostComponent implements OnInit{
  post: Post = new Post();


constructor(private activatedRoute: ActivatedRoute, private router: Router, private postService: PostService) {


}


  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe({
      next: (params: ParamMap) => {
        let postIdParam = params.get("postId");
        if (postIdParam) {
          let postId = parseInt(postIdParam); // param values are always strings
          if ( isNaN(postId) ) {
            this.router.navigateByUrl("invalidPostId"); // Undefined path will match wildcard
          } else {
            this.postService.getPost(postId).subscribe({
              next: (post) => {
                this.post = post;
              },
              error: (fail) => {
                console.error('ngOnInit(): Error getting user');
                console.error(fail);
              }

            } );
          }
        }
      },
    });

  }
}


