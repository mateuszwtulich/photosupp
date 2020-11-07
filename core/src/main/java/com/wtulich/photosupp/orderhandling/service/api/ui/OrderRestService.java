package com.wtulich.photosupp.orderhandling.service.api.ui;

import com.wtulich.photosupp.general.common.api.RestService;
import com.wtulich.photosupp.orderhandling.logic.api.to.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order/v1")
public interface OrderRestService extends RestService {

    @ApiOperation(value = "Get all orders.",
            tags = {"order"},
            response = OrderEto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 204, message = "No content found"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @GetMapping(value = "/orders",
            produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    List<OrderEto> getAllOrders();


    @ApiOperation(value = "Get order by id.",
            tags = {"order"},
            response = OrderEto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 204, message = "Entity not found"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @GetMapping(value = "/order/{id}",
            produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderEto> getOrder(@PathVariable(value = "id") Long id);


    @ApiOperation(value = "Get all comments by order id.",
            tags = {"comment"},
            response = CommentEto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 204, message = "No content found"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @GetMapping(value = "/order/{id}/comments",
            produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    List<CommentEto> getAllCommentsByOrderId(@PathVariable(name = "id") Long id);


    @ApiOperation(value = "Get all media content by order id.",
            tags = {"mediaContent"},
            response = MediaContentEto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 204, message = "No content found"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @GetMapping(value = "/order/{id}/mediaContent",
            produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    List<MediaContentEto> getAllMediaContentByOrderId(@PathVariable(name = "id") Long id);


    @ApiOperation(value = "Creates order",
            tags = {"order"},
            response = OrderEto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 422, message = "Could not process entity"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @PostMapping(value = "/order",
            consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderEto> createOrder(@Validated @RequestBody OrderTo orderTo);


    @ApiOperation(value = "Creates comment",
            tags = {"comment"},
            response = CommentEto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 422, message = "Could not process entity"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @PostMapping(value = "/order/comment",
            consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommentEto> createComment(@Validated @RequestBody CommentTo commentTo);


    @ApiOperation(value = "Add mediaContent",
            tags = {"mediaContent"},
            response = MediaContentEto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 422, message = "Could not process entity"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @PostMapping(value = "/order/mediaContent",
            consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MediaContentEto> addMediaContent(@Validated @RequestBody MediaContentTo mediaContentTo);


    @ApiOperation(value = "Updates order",
            tags = {"order"},
            response = OrderEto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 404, message = "Entity not found"),
            @ApiResponse(code = 422, message = "Could not process entity"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @PutMapping(value = "/order/{id}",
            consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderEto> updateOrder(@Validated @RequestBody OrderTo orderTo, @PathVariable(value = "id") Long id);


    @ApiOperation(value = "Updates comment",
            tags = {"comment"},
            response = CommentEto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 404, message = "Entity not found"),
            @ApiResponse(code = 422, message = "Could not process entity"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @PutMapping(value = "/order/comment/{id}",
            consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommentEto> updateComment(@Validated @RequestBody CommentTo commentTo, @PathVariable(value = "id") Long id);


    @ApiOperation(value = "Deletes order",
            tags = {"order"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 404, message = "Entity not found"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @DeleteMapping(value = "/order/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long id);


    @ApiOperation(value = "Deletes comment",
            tags = {"comment"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 404, message = "Entity not found"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @DeleteMapping(value = "/order/comment/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> deleteComment(@PathVariable(value = "id") Long id);


    @ApiOperation(value = "Deletes mediaContent",
            tags = {"mediaContent"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 404, message = "Entity not found"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @DeleteMapping(value = "/order/mediaContent/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> deleteMediaContent(@PathVariable(value = "id") Long id);


    @ApiOperation(value = "Deletes all mediaContent by order id",
            tags = {"mediaContent"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 404, message = "Entity not found"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @DeleteMapping(value = "/order/{orderId}/mediaContent",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> deleteAllMediaContent(@PathVariable(value = "orderId") Long orderId);


    @ApiOperation(value = "Finishes order",
            tags = {"order"},
            response = OrderEto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 404, message = "Entity not found"),
            @ApiResponse(code = 422, message = "Could not process entity"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @PutMapping(value = "/order/{id}/finish",
            consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderEto> finishOrder(@PathVariable(value = "id") Long id);


    @ApiOperation(value = "Accepts order",
            tags = {"order"},
            response = OrderEto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 404, message = "Entity not found"),
            @ApiResponse(code = 422, message = "Could not process entity"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @PutMapping(value = "/order/{id}/accept",
            consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderEto> acceptOrder(@PathVariable(value = "id") Long id);


    @ApiOperation(value = "Sends order to verification",
            tags = {"order"},
            response = OrderEto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "You dont have permissions for this action!"),
            @ApiResponse(code = 404, message = "Entity not found"),
            @ApiResponse(code = 422, message = "Could not process entity"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @PutMapping(value = "/order/{id}/verification",
            consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderEto> sentToVerificationOrder(@PathVariable(value = "id") Long id);
}
