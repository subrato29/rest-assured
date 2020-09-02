package postsapiobjectrepository;

public class PostsAdvanced {

			private String id;
			private String title;
			private String author;
			public InfoAdvanced[] infoAdvanced; 
			
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title;
			}
			public String getAuthor() {
				return author;
			}
			public void setAuthor(String author) {
				this.author = author;
			}
			
			public InfoAdvanced[] getInfoAdvanced(){
					return infoAdvanced;
			}
			public void setInfoAdvanced (InfoAdvanced[] infoAdvanced){
					this.infoAdvanced = infoAdvanced;
			}
			
	
}
