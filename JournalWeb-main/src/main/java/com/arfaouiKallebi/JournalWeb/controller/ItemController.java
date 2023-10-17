package com.arfaouiKallebi.JournalWeb.controller;

import com.arfaouiKallebi.JournalWeb.controller.api.ItemControllerApi;
import com.arfaouiKallebi.JournalWeb.dto.ItemDTO;
import com.arfaouiKallebi.JournalWeb.model.Attachment;
import com.arfaouiKallebi.JournalWeb.model.Item;
import com.arfaouiKallebi.JournalWeb.services.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ItemController implements ItemControllerApi {

    @Autowired
    private ObjectMapper objectMapper ;
    @Autowired
    private ItemService itemService ;

    @Override
    public ItemDTO getItemById(@PathVariable Long id){
        return itemService.findById(id) ;
    }

    @Override
    public ResponseEntity<?> deleteItemById(@PathVariable Long id){
        return itemService.deleteById(id);
    }

    @Override
    public ItemDTO saveItem(@PathVariable Long idman , @RequestParam("model") String item , @RequestParam("file") MultipartFile file) throws Exception {
        Item itemObj = objectMapper.readValue(item ,Item.class) ;
        ItemDTO itemDTO = ItemDTO.fromEntity(itemObj) ;
        return itemService.save( idman,  itemDTO , file); }

    @Override
    public ResponseEntity<Resource> downloadFile(@PathVariable Long iditem , @PathVariable String fileId) throws Exception {
        Attachment attachment = null;

        attachment = itemService.findById(iditem).getAttachment() ;
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

    @Override
    public List<ItemDTO> getItems(@PathVariable Long idman){
        return itemService.getItems(idman) ;
    }
}
