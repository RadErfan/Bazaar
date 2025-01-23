package ir.dunijet.dunibazaar.model.repository.comment

import ir.dunijet.dunibazaar.model.data.Comment

interface CommentRepository {

    suspend fun getAllComments(productId :String) :List<Comment>
    suspend fun addNewComment(productId: String , text :String , IsSuccess :(String) -> Unit)

}