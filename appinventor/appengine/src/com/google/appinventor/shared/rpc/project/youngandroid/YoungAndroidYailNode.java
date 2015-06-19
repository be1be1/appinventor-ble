// Copyright 2012 Massachusetts Institute of Technology. All rights reserved.
package com.google.appinventor.shared.rpc.project.youngandroid;

import com.google.appinventor.shared.storage.StorageUtil;
import com.google.appinventor.shared.youngandroid.YoungAndroidSourceAnalyzer;


/**
 * Young Android yail file node in the project tree (stored as a source file, even though it
 * is generated by the blocks editor)
 *
 * @author sharon@google.com (Sharon Perl)
 */
public final class YoungAndroidYailNode extends YoungAndroidSourceNode {

  /**
   * Default constructor (for serialization only).
   */
  public YoungAndroidYailNode() {
  }

  /**
   * Creates a new Young Android yail  file project node.
   *
   * @param fileId  file id
   */
  public YoungAndroidYailNode(String fileId) {
    super(StorageUtil.basename(fileId), fileId);
  }

  public static String getYailFileId(String qualifiedName) {
    return SRC_PREFIX + qualifiedName.replace('.', '/') 
        + YoungAndroidSourceAnalyzer.YAIL_FILE_EXTENSION;
  }
}
