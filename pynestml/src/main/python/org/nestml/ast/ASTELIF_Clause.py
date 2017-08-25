"""
@author kperun
TODO header
"""


class ASTELIF_Clause:
    """
    This class is used to store elif-clauses.
    Grammar:
        elif_Clause : 'elif' expression BLOCK_OPEN block;
    """
    __condition = None
    __block = None

    def __init__(self, _condition=None, _block=None):
        """
        Standard constructor.
        :param _condition: the condition of the block.
        :type _condition: ASTExpression
        :param _block: a block of statements.
        :type _block: ASTBlock
        """
        self.__block = _block
        self.__condition = _condition

    @classmethod
    def makeASTELIF_Clause(cls, _condition=None, _block=None):
        """
        The factory method of the ASTELIF_Clause class.
        :param _condition: the condition of the block.
        :type _condition: ASTExpression
        :param _block: a block of statements.
        :type _block: ASTBlock
        :return: a new block
        :rtype: ASTELIF_Clause
        """
        return cls(_condition, _block)

    def getCondition(self):
        """
        Returns the condition of the block.
        :return: the condition.
        :rtype: ASTExpression
        """
        return self.__condition

    def getBlock(self):
        """
        Returns the block of statements.
        :return: the block of statements.
        :rtype: ASTBlock
        """
        return self.__block

    def printAST(self):
        """
        Returns a string representation of the elif clause.
        :return: a string representation of the elif clause.
        :rtype: str
        """
        return 'elif ' + self.getCondition().printAST() + ':\n' + self.getBlock().printAST()
