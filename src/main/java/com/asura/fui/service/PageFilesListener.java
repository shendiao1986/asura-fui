package com.asura.fui.service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.asura.fui.service.dispatch.PageLoader; 

public class PageFilesListener implements ServletContextListener {

    private NIOWatchService watchService;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Server is initialized.");
		String pages_directory = StaticCacheService.getSettingProperty("page_directory")==null?"/pages":StaticCacheService.getSettingProperty("page_directory");
		System.out.println("Configured page directory is: " + pages_directory);
		int timeout = Integer.parseInt(StaticCacheService.getSettingProperty("page_timeout")==null?"10":StaticCacheService.getSettingProperty("page_timeout"));
		initialize(pages_directory,timeout);
		//String pages_path = this.getClass().getClassLoader().getResource(pages_directory).getPath();
		//System.out.println("Start watch page directory:" + pages_path.substring(1));
		watchService = new NIOWatchService(pages_directory);
		new Thread(watchService).start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Server is closed.");
		watchService.setStop(true);
	}
	
	public void initialize(String pages_directory,int timeout){
		PageLoader.init(pages_directory, timeout);
		PageLoader.loadConfigFromDirectory(pages_directory);
	}
	
	public class NIOWatchService implements Runnable{

		private Path path;
		
		private boolean isStop;
		
		public NIOWatchService(String spath) {
			super();
			this.path = Paths.get(spath);
		}
		
		public NIOWatchService(Path path) {
			super();
			this.path = path;
		}
		
		public boolean isStop() {
			return isStop;
		}

		public void setStop(boolean isStop) {
			this.isStop = isStop;
		}

		@Override
		public void run() {
			try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
	            
	            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,  
	                    StandardWatchEventKinds.ENTRY_MODIFY,  
	                    StandardWatchEventKinds.ENTRY_DELETE);  
	              
	            while (!isStop) {
	                final WatchKey key = watchService.take();  
	                for (WatchEvent<?> watchEvent : key.pollEvents()) {
	                    final Kind<?> kind = watchEvent.kind();
	                    final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;  
	                    final Path filename = watchEventPath.context();  
	                    System.out.println(kind + " -> " + filename);
	                    if(filename.toString().endsWith(".xml")){
	                       if(kind == StandardWatchEventKinds.ENTRY_CREATE || kind == StandardWatchEventKinds.ENTRY_MODIFY){  
	                    	   System.out.println("updating for: " + filename);   
	                    	   PageLoader.updateCache(filename.toString().substring(0, filename.toString().length()-4));	                                  
	                       }  
	                       if(kind == StandardWatchEventKinds.ENTRY_DELETE){  
	                    	   System.out.println("deleting for: " + filename);
	                    	   PageLoader.invalidCache(filename.toString().substring(0, filename.toString().length()-4));
	                       } 
	                    }
	                }//end for
	                // reset the key
	                boolean valid = key.reset();  
	                // exit loop if the key is not valid (if the directory was deleted)  
	                if (!valid) {
	                    break;  
	                }
	            }//end while
	        } catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("exit watch service.");
		}
		
	}
	

}
